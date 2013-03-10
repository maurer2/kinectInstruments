package main;

import kinect.Kinect;
import player.PlayerControll;
import processing.core.PApplet;
import screen.Screen;
import SimpleOpenNI.SimpleOpenNI;

public class Main extends PApplet {
	private Screen mainScreen;
	private Kinect kinect;

	public PlayerControll playerControl;

	public void setup() {
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

		// Player controll
		playerControl = new PlayerControll(this, kinect.context());
	}

	public void draw() {
		// Update Kinect
		kinect.update();

		// Update Main Screen
		mainScreen.update();

		// Playercontroll;
		// playerControl.update();

	}

	public SimpleOpenNI getContext() {
		return kinect.context();
	}

	public Kinect getKinect() {
		return kinect;
	}

}