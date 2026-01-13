package org.explement.utils;


import javafx.scene.paint.Color;

public final class ColorUtils {
    private ColorUtils() {}

    public static final Color FALLBACK_COLOR = Color.BLACK;
    public static String convertToRGB(double r, double g, double b) {
        return String.format("%d, %d, %d",
            (int)(r * 255),
            (int)(g * 255),
            (int)(b * 255));
    }

    public static String convertToHEX(int r, int g, int b) {
        return String.format("#%02x%02x%02x", r, g, b);
    }

    public static String convertToHSB(double h, double s, double b) {
        return String.format("%d, %d, %d", 
        (int) h, 
        (int) (s * 100), 
        (int) (b* 100));
    }

    public static String formatColor(Color color, ColorType type) {
        double r = color.getRed();
        double g = color.getGreen();
        double b = color.getBlue();

        if (type == ColorType.RGB) {
            return "rgb(" + convertToRGB(r, g, b) + ")";
        } else if (type == ColorType.HEX) {
            int ri = (int) (r * 255);
            int gi = (int) (g * 255);
            int bi = (int) (b * 255);
            return convertToHEX(ri, gi, bi);
        } else if (type == ColorType.HSB) { 
            double h = color.getHue();
            double s = color.getSaturation();
            double br = color.getBrightness();
            return "hsb(" + convertToHSB(h, s, br) + ")";
        } 

        throw new IllegalArgumentException("Color type of " + type + " is not supported");
    }
}
