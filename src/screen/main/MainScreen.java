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
	private PImage playerIcon;

	public MainScreen(Main p, Kinect kinect) {
		this.p = p;
		this.kinect = kinect;

		// GUI
		gui = new OverlayGUI(p);
		// Setup Screen
		setup();
	}

	private void setup() {
		// Styling
		p.size(1024, 768);
		p.frameRate = 60;
		p.noStroke();
		p.smooth();

		// Icons
		handIcon = p.loadImage("hand.png");
		waitIcon = p.loadImage("wait.png");
		playerIcon = p.loadImage("player.png");
	}

	public void update() {
		// Icon
		currentIcon = handIcon;

		// Translate lower left;
		// p.scale(p.width / 640f);

		// GUI
		boolean hover = gui.update();
		if (hover) {
			currentIcon = waitIcon;
		}

		// Draw Player
		drawPlayer();
	}

	private void drawPlayer() {
		int imageWidth = 40;

		for (Player player : kinect.getPlayers()) {

			p.image(currentIcon, player.getHandLeft().x - imageWidth / 2, player.getHandLeft().y
					- imageWidth / 2, imageWidth, imageWidth);
			p.image(currentIcon, player.getHandRight().x - imageWidth / 2, player.getHandRight().y
					- imageWidth / 2, imageWidth, imageWidth);
			p.image(playerIcon, player.getTorso().x - imageWidth / 2, player.getTorso().y
					- imageWidth / 2, imageWidth, imageWidth);
		}

	}
}
