package org.explement.service;

import java.util.ArrayList;
import java.util.List;

import org.explement.model.ColorModel;
import org.explement.utils.ColorType;
import org.explement.utils.ColorUtils;

import javafx.scene.paint.Color;

public class ColorService {
    private static final Color FALLBACK_COLOR = ColorUtils.FALLBACK_COLOR;
    
    private boolean isGrabbing = false;
    private ColorModel colorModel;

    private final ColorPicker colorPicker;
    private final ClipboardHandler clipboardHandler;

    private List<Runnable> colorUpdateListeners = new ArrayList<>();

    public ColorService(ColorPicker colorPicker, ClipboardHandler clipboardHandler) { // ? For testing only
        this.colorPicker = colorPicker;
        this.clipboardHandler = clipboardHandler;
    }

    public Color getColor(Integer x, Integer y) {
        if (x == null || y == null) return FALLBACK_COLOR;
        try { 
            return colorPicker.getPixelColor(x, y);
        } catch (Exception e) {
            e.printStackTrace();
            return FALLBACK_COLOR;
        }
    }
    
    public void copyToClipboard(String code) {
        if (clipboardHandler == null) return;
        if (code == null || code.isEmpty()) return;
        clipboardHandler.putString(code);
    }

    public void updateColor(Color color) {
        if (color == null) return;

        if (colorModel == null) {
            ColorModel newColorModel = new ColorModel(color, ColorType.RGB);
            setColorModel(newColorModel);
            return;
        }

        if (!colorModel.getColor().equals(color)) {
            ColorModel newColorModel = new ColorModel(color, colorModel.getColorType());
            setColorModel(newColorModel);
        } 

    }

    public void setColorType(ColorType colorType) {
        if (colorModel != null && colorModel.getColorType() != colorType) {
            ColorModel newColorModel = colorModel.withColorType(colorType);
            setColorModel(newColorModel);
        }
    }

    public ColorModel getColorModel() {
        return this.colorModel;
    }

    public void setColorModel(ColorModel colorModel) {
        this.colorModel = colorModel;
        copyToClipboard(colorModel.getColorCode());
    }

    public boolean isGrabbing() {
        return isGrabbing;
    }

    public void setIsGrabbing(boolean isGrabbing) {
        this.isGrabbing = isGrabbing;
    }
    
    public ColorType getColorType() {
        return (colorModel != null) ? colorModel.getColorType() : ColorType.RGB;
    }

    public void registerColorUpdateListener(Runnable listener) { colorUpdateListeners.add(listener); }
    public void unregisterColorUpdateListener(Runnable listener) { colorUpdateListeners.remove(listener); }

    private void fireColorUpdateListeners() {
        colorUpdateListeners.forEach(Runnable::run);
    }
}
