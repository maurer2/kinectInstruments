package main.screen;

import java.util.ArrayList;
import java.util.List;

import kinect.Kinect;
import main.IUpdate;
import main.Main;
import player.Player;
import processing.core.PGraphics;

public class Screen implements IUpdate {
	private Main p;
	private Kinect kinect;
	private PGraphics kinectStage;

	private Button button;
	private Button button2;
	private Button button3;
	private Button button4;

	private boolean DEBUG = true;

	public Screen(Main p, Kinect kinect) {
		this.p = p;
		this.kinect = kinect;
		this.kinectStage = p.createGraphics(kinect.context().sceneWidth(), kinect.context()
				.sceneHeight());

		// 146 | 12
		this.button = new Button(p, 140,70,20,10);
		this.button2 = new Button(p, 140,70,170,10);
		this.button3 = new Button(p, 140,70,320,10);
		this.button4 = new Button(p, 140,70,470,10);
	}

	public void update() {
		// Background
		p.background(0);

		// Translate lower left;
		p.scale(p.width / 640f);		

		// draw depthImageMap
		// p.image(kinect.context().rgbImage(), 0, 0,768,576);
		p.image(kinect.context().rgbImage(), 0, 0);

		// p.image(kinectStage, 384, 288);

		p.pushStyle();
		p.fill(0);
		p.rect(0, 0, 640, 90);
		p.rect(0, 0, 170, 480);
		p.popStyle();
		
		// Buttons
		p.pushStyle();
		p.fill(255);
		button.draw();
		button2.draw();
		button3.draw();
		button4.draw();
		p.popStyle();		

		// Draw Player
		if (DEBUG) {
			drawPlayer();
		}
	}

	public PGraphics getKinectStage() {
		return kinectStage;
	}

	private void drawPlayer() {

		for (Player player : kinect.getPlayers()) {

			p.ellipse(player.getHandLeft().x, player.getHandLeft().y, 20, 20);
			p.ellipse(player.getHandRight().x, player.getHandRight().y, 20, 20);

		}

	}

}
