package main;

import kinect.Kinect;
import player.PlayerControll;
import processing.core.PApplet;
import screen.Screen;
import SimpleOpenNI.SimpleOpenNI;

public class Main extends PApplet {
	private SimpleOpenNI context;
	private Screen mainScreen;

	public PlayerControll playerControl;

	public void setup() {
		size(1024, 768);
		frameRate = 60;
		stroke(255, 0, 255);
		strokeWeight(2);
		smooth();

		// Kinect init
		context = new Kinect(this).getKinect();

		// Main Screen
		mainScreen = new Screen(this, context);

		// Player controll
		playerControl = new PlayerControll(this, context);
	}

	public void draw() {

		// Update Kinect
		context.update();

		// Update Main Screen
		mainScreen.update();

		// Playercontroll;
		//playerControl.update();

	}

	public SimpleOpenNI getContext() {
		return context;
	}

}