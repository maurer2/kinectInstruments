package screen.control;

import kinect.Kinect;
import processing.core.PApplet;
import controlP5.Chart;
import controlP5.ControlP5;

public class ControlTabs {
	private ControlScreenMain c;
	private ControlP5 cp5;
	private Kinect kinect;

	public ControlTabs(ControlScreenMain c, ControlP5 cp5, Kinect kinect) {
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
		cp5.addChart("Angle Left").setPosition(30, 400).setSize(380, 105).setRange(-20, 200)
				.setView(Chart.AREA).addDataSet("angle left").setColorBackground(c.color(255, 100))
				.setColors("angle left", c.color(255)).setData("angle left", framerate)
				.moveTo("default");

		cp5.addChart("Velocity Left").setPosition(30, 545).setSize(380, 105).setRange(-140, +140)
				.setView(Chart.LINE).addDataSet("velocity left")
				.setColorBackground(c.color(255, 100)).setColors("velocity left", c.color(255))
				.setData("velocity left", framerate).moveTo("default");

		cp5.addChart("Hitdetection Left").setPosition(30, 690).setSize(380, 105)
				.setRange(-0.2f, 1.2f).setView(Chart.LINE).addDataSet("hitdetection left")
				.setColorBackground(c.color(255, 100)).setColors("hitdetection left", c.color(255))
				.setData("hitdetection left", framerate).moveTo("default");

		// Charts Right
		cp5.addChart("Angle Right").setPosition(460, 400).setSize(380, 105).setRange(-20, 200)
				.setView(Chart.AREA).addDataSet("angle right")
				.setColorBackground(c.color(255, 100)).setColors("angle right", c.color(255))
				.setData("angle right", framerate).moveTo("default");

		cp5.addChart("Velocity Right").setPosition(460, 545).setSize(380, 105).setRange(-140, +140)
				.setView(Chart.LINE).addDataSet("velocity right")
				.setColorBackground(c.color(255, 100)).setColors("velocity right", c.color(255))
				.setData("velocity right", framerate).moveTo("default");

		cp5.addChart("Hitdetection Right").setPosition(460, 690).setSize(380, 105)
				.setRange(-0.2f, 1.2f).setView(Chart.LINE).addDataSet("hitdetection right")
				.setColorBackground(c.color(255, 100))
				.setColors("hitdetection right", c.color(255))
				.setData("hitdetection right", framerate).moveTo("default");
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

			cp5.addTextlabel("Angle Left Label")
					.setText(angleLeft.getLabel())
					.setColor(c.color(236, 9, 140))
					.setPosition(angleLeft.getAbsolutePosition().x,
							angleLeft.getAbsolutePosition().y - 30);

			cp5.addTextlabel("Angle Right Label")
					.setText(angleRight.getLabel())
					.setColor(c.color(236, 9, 140))
					.setPosition(angleRight.getAbsolutePosition().x,
							angleRight.getAbsolutePosition().y - 30);

			Chart velocityLeft = (Chart) cp5.getController("Velocity Left");
			Chart velocityRight = (Chart) cp5.getController("Velocity Right");

			cp5.addTextlabel("Velocity Left Label")
					.setText(velocityLeft.getLabel())
					.setColor(c.color(236, 9, 140))
					.setPosition(velocityLeft.getAbsolutePosition().x,
							velocityLeft.getAbsolutePosition().y - 30);

			cp5.addTextlabel("Velocity Right Label")
					.setText(velocityRight.getLabel())
					.setColor(c.color(236, 9, 140))
					.setPosition(velocityRight.getAbsolutePosition().x,
							velocityRight.getAbsolutePosition().y - 30);

			Chart hitLeft = (Chart) cp5.getController("Hitdetection Left");
			Chart hitRight = (Chart) cp5.getController("Hitdetection Right");

			cp5.addTextlabel("Hitdetection Left Label")
					.setText(hitLeft.getLabel())
					.setColor(c.color(236, 9, 140))
					.setPosition(hitLeft.getAbsolutePosition().x,
							hitLeft.getAbsolutePosition().y - 30);

			cp5.addTextlabel("Hitdetection Right Label")
					.setText(hitRight.getLabel())
					.setColor(c.color(236, 9, 140))
					.setPosition(hitRight.getAbsolutePosition().x,
							hitRight.getAbsolutePosition().y - 30);

			angleLeft.push(kinect.getPlayers().get(0).getAngleLeft());
			angleRight.push(kinect.getPlayers().get(0).getAngleRight());

			velocityLeft.push(kinect.getPlayers().get(0).getVelocityLeft());
			velocityRight.push(kinect.getPlayers().get(0).getVelocityRight());

			hitLeft.push(kinect.getPlayers().get(0).getHitLeftNumeric());
			hitRight.push(kinect.getPlayers().get(0).getHitRightNumeric());
		}

		if (cp5.getTab("default").isActive()) {
			c.image(kinect.context().depthImage(), 30, 70, 380, 285);
			c.image(kinect.context().rgbImage(), 460, 70, 380, 285);
		}
	}

}
