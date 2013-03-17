package screen.control;

import kinect.Kinect;
import processing.core.PApplet;
import controlP5.Chart;
import controlP5.ControlP5;

public class ControlTab {
	private ControlScreenMain c;
	private ControlP5 cp5;
	private Kinect kinect;

	public ControlTab(ControlScreenMain c, ControlP5 cp5, Kinect kinect) {
		this.c = c;
		this.cp5 = cp5;
		this.kinect = kinect;

		// Create Tabs
		createTabs();
	}

	private void createTabs() {

		cp5.addTab("default").setColorBackground(c.color(0, 160, 100)).setColorLabel(c.color(255))
				.setColorActive(c.color(255, 128, 0)).setHeight(50).activateEvent(true)
				.setLabel("Kinect Status").setId(1);

		cp5.addTab("guitar").setColorBackground(c.color(0, 160, 100)).setColorLabel(c.color(255))
				.setColorActive(c.color(255, 128, 0)).setHeight(50).activateEvent(true)
				.setLabel("Guitar").setId(2);

		cp5.addTab("drums").setColorBackground(c.color(0, 160, 100)).setColorLabel(c.color(255))
				.setColorActive(c.color(255, 128, 0)).setHeight(50).activateEvent(true)
				.setLabel("Drums").setId(3);

		cp5.addTab("contrabass").setColorBackground(c.color(0, 160, 100))
				.setColorLabel(c.color(255)).setColorActive(c.color(255, 128, 0)).setHeight(50)
				.activateEvent(true).setLabel("Contrabass").setId(4);

		// Create Tabs
		createTabKinect();
		createTabGuitar();
	}

	private void createTabKinect() {
		float[] framerate = new float[180];

		// Charts Left
		cp5.addChart("Angle Left").setPosition(20, 330).setSize(320, 100).setRange(0, 200)
				.setView(Chart.AREA).addDataSet("angle left").setColorBackground(c.color(255, 100))
				.setData("angle left", framerate).moveTo("default");

		cp5.addChart("Velocity Left").setPosition(20, 450).setSize(320, 100).setRange(0, 200)
				.setView(Chart.AREA).addDataSet("velocity left")
				.setColorBackground(c.color(255, 100)).setData("velocity left", framerate)
				.moveTo("default");

		cp5.addChart("Hitdetection Left").setPosition(20, 570).setSize(320, 100).setRange(0, 200)
				.setView(Chart.AREA).addDataSet("hitdetection left")
				.setColorBackground(c.color(255, 100)).setData("hitdetection left", framerate)
				.moveTo("default");

		// Charts Right

		cp5.addChart("Angle Right").setPosition(360, 330).setSize(320, 100).setRange(0, 200)
				.setView(Chart.AREA).addDataSet("angle right")
				.setColorBackground(c.color(255, 100)).setData("angle right", framerate)
				.moveTo("default");

		cp5.addChart("Velocity Right").setPosition(360, 450).setSize(320, 100).setRange(0, 200)
				.setView(Chart.AREA).addDataSet("velocity right")
				.setColorBackground(c.color(255, 100)).setData("velocity right", framerate)
				.moveTo("default");

		cp5.addChart("Hitdetection Right").setPosition(360, 570).setSize(320, 100).setRange(0, 200)
				.setView(Chart.AREA).addDataSet("hitdetection right")
				.setColorBackground(c.color(255, 100)).setData("hitdetection right", framerate)
				.moveTo("default");

	}

	private void createTabGuitar() {
		// Buttons
		cp5.addButton("button").setBroadcast(false).setPosition(100, 100).setSize(80, 40)
				.setValue(1).moveTo("guitar").setBroadcast(true).getCaptionLabel()
				.align(PApplet.CENTER, PApplet.CENTER);

		cp5.addSlider("slider").setBroadcast(false).setRange(100, 200).setValue(128)
				.setPosition(100, 160).setSize(200, 20).setBroadcast(true);

		cp5.addSlider("sliderValue").setBroadcast(false).setRange(0, 255).setValue(128)
				.setPosition(100, 200).setSize(200, 20).setBroadcast(true);

		cp5.getController("sliderValue").moveTo("drums");
		cp5.getController("slider").moveTo("drums");

	}

	private void createTabDrums() {

	}

	private void createTabContrabass() {

	}

	public void update() {
		c.background(0);

		if (kinect.getPlayers().size() > 0) {

			Chart angle = (Chart) cp5.getController("Angle Left");
			angle.push(kinect.getPlayers().get(0).getAngleLeft());
		}

		if (cp5.getTab("default").isActive()) {

			c.image(kinect.context().depthImage(), 20, 70, 320, 240);
			c.image(kinect.context().rgbImage(), 360, 70, 320, 240);
		}
	}

}
