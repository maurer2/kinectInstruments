package screen.control;

import processing.core.PApplet;
import processing.core.PFont;
import controlP5.ControlEvent;
import controlP5.ControlP5;

public class ControlTab {
	private ControlScreenMain c;
	private ControlP5 cp5;

	public ControlTab(ControlScreenMain c, ControlP5 cp5) {
		this.c = c;
		this.cp5 = cp5;

		// Create Tabs
		createTabs();
	}

	private void createTabs() {

		cp5.addTab("default").setColorBackground(c.color(0, 160, 100)).setColorLabel(c.color(255))
				.setColorActive(c.color(255, 128, 0)).setHeight(50);

		cp5.addTab("extra").setColorBackground(c.color(0, 160, 100)).setColorLabel(c.color(255))
				.setColorActive(c.color(255, 128, 0)).setHeight(50);

		cp5.addTab("drums").setColorBackground(c.color(0, 160, 100)).setColorLabel(c.color(255))
				.setColorActive(c.color(255, 128, 0)).setHeight(50);

		cp5.addTab("contrabass").setColorBackground(c.color(0, 160, 100))
				.setColorLabel(c.color(255)).setColorActive(c.color(255, 128, 0)).setHeight(50);

		cp5.getTab("default").activateEvent(true).setLabel("Kinect Status").setId(1);
		cp5.getTab("extra").activateEvent(true).setLabel("Guitar").setId(2);
		cp5.getTab("drums").activateEvent(true).setLabel("Drums").setId(3);
		cp5.getTab("contrabass").activateEvent(true).setLabel("Contrabass").setId(4);

		// Create Tabs
		createTabKinect();
	}

	private void createTabKinect() {

		cp5.addButton("button").setBroadcast(false).setPosition(100, 100).setSize(80, 40)
				.setValue(1).setBroadcast(true).getCaptionLabel()
				.align(PApplet.CENTER, PApplet.CENTER);

		cp5.addButton("buttonValue").setBroadcast(false).setPosition(220, 100).setSize(80, 40)
				.setValue(2).setBroadcast(true).getCaptionLabel()
				.align(PApplet.CENTER, PApplet.CENTER);

		cp5.addSlider("slider").setBroadcast(false).setRange(100, 200).setValue(128)
				.setPosition(100, 160).setSize(200, 20).setBroadcast(true);

		cp5.addSlider("sliderValue").setBroadcast(false).setRange(0, 255).setValue(128)
				.setPosition(100, 200).setSize(200, 20).setBroadcast(true);

		cp5.getController("sliderValue").moveTo("extra");
		cp5.getController("slider").moveTo("drums");

	}

	private void createTabGuitar() {

	}

	public void update() {
		c.background(0);

		if (cp5.getTab("default").isActive()) {
			// System.out.println("defaul");

			c.image(c.getKinect().context().depthImage(), 20, 20, 320, 240);
			c.rect(50, 50, 50, 50);
		}
	}

}
