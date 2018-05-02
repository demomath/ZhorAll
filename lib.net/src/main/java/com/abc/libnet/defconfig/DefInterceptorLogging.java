package com.abc.libnet.defconfig;



import com.abc.libcore.rxtools.RxLogTool;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


/**
 * Created by wudi on 16-10-20.
 *
 * 拦截请求，打印log
 */
public class DefInterceptorLogging implements Interceptor {

    /**
     * 拦截
     * @param chain 数据链
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {

        //得到请求
        Request request = chain.request();
        //请求的url
        String requestUrl = getLogMessage(request.url());
        //请求时时间
        long t1 = System.nanoTime();
        //拼接请求的信息
        String requestLog = netRequestLog(requestUrl,chain.connection()
                ,request.headers() ,bodyToString(request));
        //打印
        RxLogTool.e(HttpUrl.parse(requestUrl).url().getPath());
        RxLogTool.e(requestLog);


        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        MediaType mediaType = responseBody.contentType();
        if (mediaType.toString().contains("image")) {
            return response;
        }

        String responseBodyString = response.body().string();
        Response newResponse = response.newBuilder().body(ResponseBody.create(responseBody.contentType()
                , responseBodyString.getBytes())).build();
        String responseUrl = getLogMessage(response.request().url());

        long t2 = System.nanoTime();
        String responseLog = netResponseLog(responseUrl, (t2 - t1) / 1e6d
                , response.headers(),responseBodyString);

        RxLogTool.e(HttpUrl.parse(responseUrl).url().getPath());
        RxLogTool.e(responseLog);

        return newResponse;
    }

    public static String netRequestLog(String url, Object address, Object head, Object body) {
        String addressMsg = getLogMessage(address);
        String headMsg = getLogMessage(head);
        String bodyMsg = getLogMessage(body);

        String content = "Send request to:" + url +"\n"
                +"from:" + addressMsg +"\n"
                + headMsg + "\n"
                + bodyMsg ;
        return content;
    }

    private String netResponseLog(String url, double time, Headers head, String body) {
        String timeMsg = getLogMessage(time);
        String headMsg = getLogMessage(head);
        String bodyMsg = getLogMessage(body);
        String content = "Received response from:" + url +"\n"
                +"timeout:" + timeMsg +"\n"
                + headMsg + "\n"
                +"Response-Result:\n"
                + bodyMsg ;
        return content;
    }

    /**
     * 转为String
     * @param request request
     * @return string
     */
    private static String bodyToString(final Request request){
        final Request copy = request.newBuilder().build();
        final Buffer buffer = new Buffer();
        try {
            if (copy.body() != null ) {
                copy.body().writeTo(buffer);
                return buffer.readUtf8();
            } else {
                return "body is null";
            }
        } catch (final IOException e) {
            return "did not work";
        }
    }

    private static String getLogMessage(Object... msg) {
        if (msg != null && msg.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (Object s : msg) {
                if (s != null) {
                    sb.append(s.toString());
                }
            }
            return sb.toString();
        }
        return "";
    }

}