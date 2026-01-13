package org.explement.listener;

import org.explement.controller.PrimaryController;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javafx.application.Platform;

public class KeyListener implements NativeKeyListener {
	private PrimaryController controller;
	private boolean shiftDown = false;
	private boolean ctrlDown = false;

	public KeyListener(PrimaryController controller) {
		this.controller = controller;
	}

	public void nativeKeyPressed(NativeKeyEvent e) {
		//System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

		switch (e.getKeyCode()) {
			case NativeKeyEvent.VC_SHIFT:
				shiftDown = true;
				break;
			case NativeKeyEvent.VC_CONTROL:
				ctrlDown = true;
				break;
			case NativeKeyEvent.VC_Q:
				if (!controller.getColorService().isGrabbing() && shiftDown && ctrlDown) {
					controller.getColorService().setIsGrabbing(true);
					Platform.runLater(() -> {
						controller.toggleGrab();
					});
				}
				break;
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		switch (e.getKeyCode()) {
			case NativeKeyEvent.VC_SHIFT:
				shiftDown = false;
				break;
			case NativeKeyEvent.VC_CONTROL:
				ctrlDown = false;
				break;
		}
	}
}