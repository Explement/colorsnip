package org.explement.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.explement.utils.ColorType;
import org.explement.utils.ColorUtils;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;

public class ColorModelTests {
    
    private static final ColorType RGB = ColorType.RGB;
    private static final ColorType HEX = ColorType.HEX;
    private static final Color DEFAULT_COLOR = Color.RED;

    @Test
    public void createColorModelSucceeds() {
        ColorModel colorModel = new ColorModel(DEFAULT_COLOR, RGB);

        assertEquals(colorModel.getColor(), DEFAULT_COLOR); 
        assertEquals(colorModel.getColorType(), RGB); 
    }

    @Test
    public void createColorModelWithNullColor() {
        assertThrows(NullPointerException.class, () -> {
            new ColorModel(null, RGB);
        });
    }

    @Test
    public void createColorModelWithNullColorType() {
        ColorModel colorModel = new ColorModel(DEFAULT_COLOR, null);
        ColorModel expected = new ColorModel(DEFAULT_COLOR, RGB);
        
        assertEquals(expected.getColor(), colorModel.getColor());
        assertEquals(expected.getColorType(), colorModel.getColorType());
    }

    @Test
    public void withColorTypeReturnsCorrectColorCode() {
        ColorModel colorModel = new ColorModel(DEFAULT_COLOR, RGB);
        ColorModel colorModelHex = colorModel.withColorType(HEX);

        assertNotEquals(colorModel, colorModelHex); // * Returns new instance, not copy
        assertEquals(DEFAULT_COLOR, colorModelHex.getColor());
        assertEquals(HEX, colorModelHex.getColorType());
        assertEquals(ColorUtils.formatColor(DEFAULT_COLOR, HEX), colorModelHex.getColorCode());
    }

    @Test
    public void equalColorModelsAreMarkedEqual() {
        ColorModel colorModel = new ColorModel(DEFAULT_COLOR, RGB);
        ColorModel colorModel1 = new ColorModel(DEFAULT_COLOR, RGB);
        assertEquals(colorModel, colorModel1);
    }

    @Test
    public void unequalColorModelsAreMarkedUnequal() {
        ColorModel colorModel = new ColorModel(DEFAULT_COLOR, RGB);
        ColorModel colorModel1 = new ColorModel(DEFAULT_COLOR, HEX);
        assertNotEquals(colorModel, colorModel1);
    }

    @Test
    public void equalColorModelsHaveSameHash() {
        ColorModel colorModel = new ColorModel(DEFAULT_COLOR, RGB);
        ColorModel colorModel1 = new ColorModel(DEFAULT_COLOR, RGB);
        assertEquals(colorModel, colorModel1);
        assertEquals(colorModel.hashCode(), colorModel1.hashCode());
    }

    @Test
    public void unequalColorModelsHaveDifferentHash() {
        ColorModel colorModel = new ColorModel(DEFAULT_COLOR, RGB);
        ColorModel colorModel1 = new ColorModel(DEFAULT_COLOR, HEX);
        assertNotEquals(colorModel, colorModel1);
        assertNotEquals(colorModel.hashCode(), colorModel1.hashCode());
    }
}
