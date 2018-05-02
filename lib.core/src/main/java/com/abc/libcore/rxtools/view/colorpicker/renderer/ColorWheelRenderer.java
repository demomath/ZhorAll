package com.abc.libcore.rxtools.view.colorpicker.renderer;


import com.abc.libcore.rxtools.view.colorpicker.ColorCircle;

import java.util.List;

public interface ColorWheelRenderer {
    float GAP_PERCENTAGE = 0.025f;

    void draw();

    ColorWheelRenderOption getRenderOption();

    void initWith(ColorWheelRenderOption colorWheelRenderOption);

    List<ColorCircle> getColorCircleList();
}
