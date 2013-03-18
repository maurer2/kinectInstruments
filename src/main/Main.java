package main;

import instruments.Instruments;

import java.util.List;

import kinect.Kinect;
import player.Player;
import processing.core.PApplet;
import screen.control.ControlScreen;
import screen.main.MainScreen;

public class Main extends PApplet {
	private MainScreen mainScreen;
	private ControlScreen controlScreen;
	private Kinect kinect;
	private Instruments instrument;

	public final void setup() {
		// Kinect init
		kinect = new Kinect(this, true);

		// Main Screen
		mainScreen = new MainScreen(this, kinect);

		// Control Screen
		controlScreen = new ControlScreen(this, kinect);

		// Instrument
		instrument = new Instruments(this, kinect);
		instrument.setCurrentInstrument(1);
	}

	public final void draw() {
		// Basic Settings
		background(0);
		scale(width / 640f);

		// Update Kinect
		kinect.update();

		// Check if kinect is ready
		if (!kinect.kinectReady) {
			return;
		}

		// Update Instrument
		instrument.update();

		// Update Main Screen
		mainScreen.update();
	}

	// Getter & Setter
	public final Kinect getKinect() {
		return kinect;
	}

	public List<Player> getPlayers() {
		return kinect.getPlayers();
	}

	public final Instruments getInstruments() {
		return instrument;

	}

}