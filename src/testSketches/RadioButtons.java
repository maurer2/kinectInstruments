package testSketches;

import processing.core.PApplet;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.RadioButton;
import controlP5.Toggle;

public class RadioButtons extends PApplet {
	private ControlP5 cp5;
	private int myColorBackground = color(0, 0, 0);
	private RadioButton coltrolBar;
	private int countdown = 0;
	private final int threshold = 1200;
	
	private int buttonWidth = 334;
	private int buttonHeight = 70;
	private int margin = 10;

	public void setup() {
		size(1024, 768);
		cursor(CROSS);
		
		cp5 = new ControlP5(this);
		coltrolBar = cp5.addRadioButton("radiobuttons")
				.setPosition(0, 0)
				.setSize(buttonWidth, buttonHeight)
				.setColorValue(0)
				.setColorForeground(color(255))
				.setColorActive(color(255,0,255))
				.setItemsPerRow(3).setSpacingColumn(11)								
				.setHeight(buttonHeight)
				.setBackgroundColor(0)
				.setBackgroundHeight(buttonHeight + margin)
				.addItem("Guitar", 0)
				.addItem("Drums", 1)
				.addItem("Contrabass", 2)
				.hideLabels();
	}

	public void draw() {	
		scale(0.625f);
		for (Toggle button : coltrolBar.getItems()) {
			if (button.isMouseOver()) {
				countdown++;

				if (countdown >= threshold) {
					// button.toggle();
					button.setValue(true);
					countdown = 0;
				}
			}
		}		
		
		scale(1.6f);		
	}

	public void controlEvent(ControlEvent theEvent) {
		if (theEvent.isFrom(coltrolBar)) {
			print("got an event from " + theEvent.getName() + "\t");
			for (int i = 0; i < theEvent.getGroup().getArrayValue().length; i++) {
				print((theEvent.getGroup().getArrayValue()[i]));
			}
			println("\t " + theEvent.getValue());
		}
	}

	public void radiobuttons(int a) {
		println("a radio Button event: " + a);
	}

}
