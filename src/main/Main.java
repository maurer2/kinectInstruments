package main;

import instruments.Instruments;
import kinect.Kinect;
import main.screen.Screen;
import processing.core.PApplet;
import control.screen.ControlScreen;
import controlP5.ControlP5;

public class Main extends PApplet {
	private Screen mainScreen;
	private ControlScreen controlScreen;
	private Kinect kinect;
	private Instruments instrument;

	ControlP5 mainFrame;

	public final void setup() {
		// Styling
		size(1024, 768);
		frameRate = 60;
		noStroke();
		smooth();

		// Kinect init
		kinect = new Kinect(this);

		// Main Screen
		mainScreen = new Screen(this, kinect);

		// Control Screen
		controlScreen = new ControlScreen(this, kinect);

		// Instrument
		instrument = new Instruments(this, kinect);
		instrument.setCurrentInstrument(2);
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
		controlScreen.update();

		// Update Instrument
		instrument.update();
	}

	public final Kinect getKinect() {
		return kinect;
	}

}