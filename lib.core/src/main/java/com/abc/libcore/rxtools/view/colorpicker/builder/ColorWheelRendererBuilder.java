package com.abc.libcore.rxtools.view.colorpicker.builder;


import com.abc.libcore.rxtools.view.colorpicker.ColorPickerView;
import com.abc.libcore.rxtools.view.colorpicker.renderer.ColorWheelRenderer;
import com.abc.libcore.rxtools.view.colorpicker.renderer.FlowerColorWheelRenderer;
import com.abc.libcore.rxtools.view.colorpicker.renderer.SimpleColorWheelRenderer;

public class ColorWheelRendererBuilder {
    public static ColorWheelRenderer getRenderer(ColorPickerView.WHEEL_TYPE wheelType) {
        switch (wheelType) {
            case CIRCLE:
                return new SimpleColorWheelRenderer();
            case FLOWER:
                return new FlowerColorWheelRenderer();
        }
        throw new IllegalArgumentException("wrong WHEEL_TYPE");
    }
}