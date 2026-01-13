package org.explement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.explement.model.ColorModel;
import org.explement.utils.ColorType;

import javafx.scene.paint.Color;

public class ColorServiceTests {
    private final Color DEFAULT_COLOR = Color.RED;
    private final Color ALTERNATE_COLOR = Color.BLUE;
    private final Color FALLBACK_COLOR = Color.BLACK;

    private final int NEGATIVE_NUMBER = -1;
    private final int POSITIVE_NUMBER = 1;

    private static final ColorType RGB = ColorType.RGB;
    private static final ColorType HEX = ColorType.HEX;
    private static final ColorType HSB = ColorType.HSB;
    
    ColorPicker colorPicker;
    ClipboardHandler clipboardHandler;
    ColorService colorService;

    @BeforeEach
    public void setup() {
        colorPicker = mock(ColorPicker.class);
        clipboardHandler = mock(ClipboardHandler.class);
        colorService = new ColorService(colorPicker, clipboardHandler);

        // * Off screen
        when(colorPicker.getPixelColor(
            NEGATIVE_NUMBER,
            NEGATIVE_NUMBER
        )).thenThrow(new RuntimeException());

        // * On screen
        when(colorPicker.getPixelColor(
            POSITIVE_NUMBER,
            POSITIVE_NUMBER
        )).thenReturn(DEFAULT_COLOR);
    }

    @Test
    public void getColorReturnsCorrectColor() {
        assertEquals(DEFAULT_COLOR, colorService.getColor(POSITIVE_NUMBER, POSITIVE_NUMBER));
    }

    @Test
    public void getColorWithNegativeCoordinatesReturnsFallbackColor() {
        assertEquals(FALLBACK_COLOR, colorService.getColor(NEGATIVE_NUMBER, NEGATIVE_NUMBER));
    }
    @Test
    public void getColorWithNullParametersReturnsFallbackColor() {
        assertEquals(FALLBACK_COLOR, colorService.getColor(null, null));
    }

    @Test
    public void updateColorWithNoExistingModelChangesColorSuccessfully() { // ? Use HEX as it's not default
        ColorModel colorModel = new ColorModel(DEFAULT_COLOR, HEX);
        colorService.setColorModel(colorModel);
        colorService.updateColor(ALTERNATE_COLOR);
        assertEquals(new ColorModel(ALTERNATE_COLOR, HEX), colorService.getColorModel());
    }

    @Test
    public void updateColorWithModelChangesColorSuccessfully() {
        colorService.updateColor(ALTERNATE_COLOR);
        assertEquals(new ColorModel(ALTERNATE_COLOR, RGB), colorService.getColorModel());
    }

    @Test
    public void updateColorWithNoColorDoesNothing() {
        colorService.updateColor(null);
        assertEquals(null, colorService.getColorModel());
    }

    @Test
    public void updateColorSameColorNoChange() {
        colorService.updateColor(DEFAULT_COLOR);
        verify(clipboardHandler, times(1)).putString(any());
        colorService.updateColor(DEFAULT_COLOR);
        verify(clipboardHandler, times(1)).putString(any()); // * Not called again
    }

    @Test
    public void setColorModelCopiesCodeToClipboard() {
        ColorModel model = new ColorModel(DEFAULT_COLOR, RGB);
        String expected = model.getColorCode();
        colorService.setColorModel(model);
        verify(clipboardHandler, times(1)).putString(expected);
    }

    @Test   
    public void copyToClipboardPutsStringSuccessfully() {
        String expected = "abc123";
        colorService.copyToClipboard(expected);
        verify(clipboardHandler, times(1)).putString(expected);
    }

    @Test
    public void copyToClipboardWithNullParametersDoesNotThrow() {
        colorService.copyToClipboard(null);
        verify(clipboardHandler, times(0)).putString(any());
    }

    @Test
    public void copyToClipboardWithNullClipboardHandlerDoesNotThrow() {
        assertDoesNotThrow(() -> {
            ColorService cs = new ColorService(colorPicker, null);
            cs.copyToClipboard("abc123");
        });
    }

    @Test
    public void copyToClipboardWithEmptyStringDoesNotThrow() {
        colorService.copyToClipboard("");
        verify(clipboardHandler, times(0)).putString(any());
    }

    @Test
    public void setColorTypeCopiesToClipboard() {
        ColorModel model = new ColorModel(DEFAULT_COLOR, RGB);
        colorService.setColorModel(model);
        colorService.setColorType(HEX);
        String code = model.withColorType(HEX).getColorCode();
        verify(clipboardHandler, times(1)).putString(code);
    }

        @Test
    public void setColorTypeUpdatesTypeCorrectly() {
        ColorModel model = new ColorModel(DEFAULT_COLOR, RGB);
        colorService.setColorModel(model);
        colorService.setColorType(HEX);
        assertEquals(HEX, colorService.getColorType());
    }
}