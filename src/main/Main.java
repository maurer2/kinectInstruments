package main;

import instruments.Instruments;
import kinect.Kinect;
import processing.core.PApplet;
import screen.control.ControlScreen;
import screen.main.MainScreen;
import controlP5.ControlP5;

public class Main extends PApplet {
	private MainScreen mainScreen;
	private ControlScreen controlScreen;
	private Kinect kinect;
	private Instruments instrument;

	private ControlP5 mainFrame;

	public final void setup() {

		// Kinect init
		kinect = new Kinect(this);

		// Main Screen
		mainScreen = new MainScreen(this, kinect);

		// Control Screen
		controlScreen = new ControlScreen(this, kinect);

		// Instrument
		instrument = new Instruments(this, kinect);
		instrument.setCurrentInstrument(4);
	}

	public final void draw() {
		// Update Kinect
		kinect.update();

		// Check if kinect is ready
		if (!kinect.kinectReady) {
			return;
		}

		// Update Main Screen
		mainScreen.update();

		// Update Control Screen
		// controlScreen.update();

		// Update Instrument
		instrument.update();
	}

	public final Kinect getKinect() {
		return kinect;
	}

}