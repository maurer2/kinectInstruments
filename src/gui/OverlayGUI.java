package gui;

import main.Main;
import processing.core.PApplet;
import sun.security.ssl.ProtocolVersion;
import controlP5.ControlP5;
import controlP5.RadioButton;
import controlP5.Toggle;

public class OverlayGUI {
	private Main p;
	private ControlP5 cp5;
	private RadioButton controlBar;

	private int buttonWidth = 210;
	private int buttonHeight = 60;
	private int margin = 10;

	private int countdown = 0;
	private final int threshold = 120;

	public OverlayGUI(Main p) {
		this.p = p;
		cp5 = new ControlP5(p);

		// Controlbar erstellen
		createBar();
	}

	private void createBar() {
		controlBar = cp5.addRadioButton("radiobuttons").setPosition(0, 0)
				.setSize(buttonWidth, buttonHeight).setColorValue(0)
				.setColorForeground(p.color(255)).setColorActive(p.color(255, 0, 255))
				.setItemsPerRow(3).setSpacingColumn(10).setHeight(buttonHeight)
				.setBackgroundColor(0).setBackgroundHeight(buttonHeight + margin)
				.addItem("Guitar", 0).addItem("Drums", 1).addItem("Contrabass", 2).hideLabels();
	}

	public void update() {
	
		for (Toggle button : controlBar.getItems()) {
			
			if (button.isMouseOver()) {
				countdown++;
				//System.out.println("Mouse inside");

				if (countdown >= threshold) {
					// button.toggle();
					button.setValue(true);
					countdown = 0;
				}
			}
		}

	}

	public int getHeight() {
		return controlBar.getHeight();
	}

	public int getWidth() {
		return controlBar.getWidth();
	}
}
