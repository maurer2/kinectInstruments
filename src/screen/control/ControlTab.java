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
		createChartsKinect();
		createChartsGuitar();
		createChartsDrums();
	}

	private void createChartsKinect() {
		float[] framerate = new float[180];

		// Charts Left
		cp5.addChart("Angle Left").setPosition(20, 330).setSize(320, 100).setRange(0, 200)
				.setView(Chart.AREA).addDataSet("angle left").setColorBackground(c.color(255, 100))
				.setData("angle left", framerate).moveTo("default");

		cp5.addChart("Velocity Left").setPosition(20, 450).setSize(320, 100).setRange(-100, +100)
				.setView(Chart.LINE).addDataSet("velocity left")
				.setColorBackground(c.color(255, 100)).setData("velocity left", framerate)
				.moveTo("default");

		cp5.addChart("Hitdetection Left").setPosition(20, 570).setSize(320, 100).setRange(0, 1.2f)
				.setView(Chart.AREA).addDataSet("hitdetection left")
				.setColorBackground(c.color(255, 100)).setData("hitdetection left", framerate)
				.moveTo("default");

		// Charts Right

		cp5.addChart("Angle Right").setPosition(360, 330).setSize(320, 100).setRange(0, 200)
				.setView(Chart.AREA).addDataSet("angle right")
				.setColorBackground(c.color(255, 100)).setData("angle right", framerate)
				.moveTo("default");

		cp5.addChart("Velocity Right").setPosition(360, 450).setSize(320, 100).setRange(-100, +100)
				.setView(Chart.LINE).addDataSet("velocity right")
				.setColorBackground(c.color(255, 100)).setData("velocity right", framerate)
				.moveTo("default");

		cp5.addChart("Hitdetection Right").setPosition(360, 570).setSize(320, 100)
				.setRange(0, 1.2f).setView(Chart.AREA).addDataSet("hitdetection right")
				.setColorBackground(c.color(255, 100)).setData("hitdetection right", framerate)
				.moveTo("default");
	}

	private void createChartsGuitar() {

		cp5.addSlider("Number Strings").setBroadcast(false).setRange(1, 5).setValue(1)
				.setPosition(20, 70).setSize(50, 250).setBroadcast(true).moveTo("guitar");

		cp5.addSlider("Margin Strings").setBroadcast(false).setRange(10, 50).setValue(20)
				.setPosition(200, 70).setSize(50, 250).setBroadcast(true).moveTo("guitar");

		cp5.addSlider("Distance Neck").setBroadcast(false).setRange(50, 400).setValue(300)
				.setPosition(380, 80).setSize(50, 250).setBroadcast(true).moveTo("guitar");

		cp5.addSlider("Distance Fret").setBroadcast(false).setRange(50, 400).setValue(100)
				.setPosition(560, 70).setSize(50, 250).setBroadcast(true).moveTo("guitar");
	}

	private void createChartsDrums() {

		cp5.addSlider("Number Drumss").setBroadcast(false).setRange(1, 7).setValue(1)
				.setPosition(20, 70).setSize(50, 250).setBroadcast(true).moveTo("drums");

		cp5.addSlider("Margin Drums").setBroadcast(false).setRange(10, 80).setValue(20)
				.setPosition(200, 70).setSize(50, 250).setBroadcast(true).moveTo("drums");

		cp5.addSlider("Width Drum").setBroadcast(false).setRange(50, 200).setValue(300)
				.setPosition(380, 80).setSize(50, 250).setBroadcast(true).moveTo("drums");

		cp5.addSlider("Height Drum").setBroadcast(false).setRange(50, 200).setValue(100)
				.setPosition(560, 70).setSize(50, 250).setBroadcast(true).moveTo("drums");
	}

	private void createChartsContrabass() {

	}

	public void update() {
		c.background(0);

		if (kinect.getPlayers().size() > 0) {

			Chart angleLeft = (Chart) cp5.getController("Angle Left");
			Chart angleRight = (Chart) cp5.getController("Angle Right");
			Chart velocityLeft = (Chart) cp5.getController("Velocity Left");
			Chart velocityRight = (Chart) cp5.getController("Velocity Right");

			angleLeft.push(kinect.getPlayers().get(0).getAngleLeft());
			angleRight.push(kinect.getPlayers().get(0).getAngleRight());

			velocityLeft.push(kinect.getPlayers().get(0).getVelocityLeft());
			velocityRight.push(kinect.getPlayers().get(0).getVelocityRight());

		}

		if (cp5.getTab("default").isActive()) {

			c.image(kinect.context().depthImage(), 20, 70, 320, 240);
			c.image(kinect.context().rgbImage(), 360, 70, 320, 240);
		}
	}

}
