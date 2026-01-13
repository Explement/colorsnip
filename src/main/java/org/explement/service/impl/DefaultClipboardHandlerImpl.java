package org.explement.service.impl;

import org.explement.service.ClipboardHandler;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class DefaultClipboardHandlerImpl implements ClipboardHandler {
    @Override
    public void putString(String text) { // TODO: Dont allow copy of PREV copied
        if (text == null) return;

        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);
    }
}
