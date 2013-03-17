package screen.main;

import gui.OverlayGUI;

import java.util.ArrayList;
import java.util.List;

import kinect.Kinect;
import main.IUpdate;
import main.Main;
import player.Player;
import processing.core.PGraphics;
import processing.core.PImage;

public class MainScreen implements IUpdate {
	private Main p;
	private Kinect kinect;
	private PGraphics kinectStage;
	private OverlayGUI gui;

	private PImage currentIcon;
	private PImage handIcon;
	private PImage waitIcon;

	public MainScreen(Main p, Kinect kinect) {
		this.p = p;
		this.kinect = kinect;

		// Styling
		p.size(1024, 768);
		p.frameRate = 60;
		p.noStroke();
		p.smooth();

		// Icons
		handIcon = p.loadImage("hand.png");
		waitIcon = p.loadImage("wait.png");

		// GUI
		gui = new OverlayGUI(p);
	}

	public void update() {
		// Background
		p.background(0);

		// Icon
		currentIcon = handIcon;

		// Translate lower left;
		p.scale(p.width / 640f);
		// p.scale(1.6f);

		// GUI (ugly, I know)
		boolean hover = gui.update();
		if (hover) {
			currentIcon = waitIcon;
		}

		// Draw Player
		drawPlayer();
	}

	private void drawPlayer() {
		int imageWidth = 50;

		for (Player player : kinect.getPlayers()) {

			/*
			 * p.pushStyle(); p.fill(255, 0, 255); p.noStroke();
			 * 
			 * p.ellipse(player.getHandLeft().x, player.getHandLeft().y, 50,
			 * 50); p.ellipse(player.getHandRight().x, player.getHandRight().y,
			 * 40, 40);
			 * 
			 * p.popStyle();
			 */

			p.image(currentIcon, player.getHandLeft().x - imageWidth / 2, player.getHandLeft().y
					- imageWidth / 2, imageWidth, imageWidth);
			p.image(currentIcon, player.getHandRight().x - imageWidth / 2, player.getHandRight().y
					- imageWidth / 2, imageWidth, imageWidth);
		}

	}
}
