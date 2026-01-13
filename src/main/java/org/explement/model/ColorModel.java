package org.explement.model;

import org.explement.utils.ColorType;
import org.explement.utils.ColorUtils;

import javafx.scene.paint.Color;
import java.util.Objects;

public class ColorModel {
    private Color color;
    private String colorCode;
    private ColorType colorType;

    public ColorModel(Color color, ColorType colorType) {
        if (color == null) {
            throw new NullPointerException("Color can not be null");
        }  else {
            this.color = color;
        }

        if (colorType == null) {
            this.colorType = ColorType.RGB; // * Default
        } else {
            this.colorType = colorType;
        }

        this.colorCode = ColorUtils.formatColor(this.color, this.colorType);
    }

    public Color getColor() {
        return this.color;
    }

    public String getColorCode() {
        return this.colorCode;
    }

    public ColorType getColorType() {
        return this.colorType;
    }

    public ColorModel withColorType(ColorType type) {
        return new ColorModel(color, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof ColorModel)) return false;
        ColorModel colorModel = (ColorModel) obj;
        return (this.color.equals(colorModel.getColor()) && this.colorType.equals(colorModel.getColorType()));
    }

    @Override 
    public int hashCode() {
        return Objects.hash(color, colorType);
    }
}
