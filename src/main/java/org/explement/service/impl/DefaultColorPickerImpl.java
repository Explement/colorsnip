package org.explement.service.impl;

import org.explement.service.ColorPicker;

import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;

public class DefaultColorPickerImpl implements ColorPicker {
    private final Robot robot;

    public DefaultColorPickerImpl() {
        this.robot = new Robot();
    }

    @Override
    public Color getPixelColor(int x, int y) {
        return robot.getPixelColor(x, y);
    }


}
