package main;

import main.screen.Screen;
import instruments.Instruments;
import kinect.Kinect;
import processing.core.PApplet;

public class Main extends PApplet {
	private Screen mainScreen;
	private Kinect kinect;
	private Instruments instrument;

	public final void setup() {
		// Styling
		size(1024, 768);
		frameRate = 60;
		stroke(255, 0, 255);
		strokeWeight(2);
		smooth();

		// Kinect init
		kinect = new Kinect(this);

		// Main Screen
		mainScreen = new Screen(this, kinect);

		// Instrument
		instrument = new Instruments(this, kinect, mainScreen.getKinectStage());
		instrument.setCurrentInstrument(1);
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
		
		// Update Instrument
		instrument.update();
	}

	public final Kinect getKinect() {
		return kinect;
	}

}