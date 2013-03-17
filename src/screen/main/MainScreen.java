package screen.main;

import gui.OverlayGUI;

import java.util.ArrayList;
import java.util.List;

import kinect.Kinect;
import main.IUpdate;
import main.Main;
import player.Player;
import processing.core.PGraphics;

public class MainScreen implements IUpdate {
	private Main p;
	private Kinect kinect;
	private PGraphics kinectStage;
	private OverlayGUI gui;

	private boolean DEBUG = true;

	public MainScreen(Main p, Kinect kinect) {
		this.p = p;
		this.kinect = kinect;

		// Styling
		p.size(1024, 768);
		p.frameRate = 60;
		p.noStroke();
		p.smooth();

		// GUI
		gui = new OverlayGUI(p);
	}

	public void update() {
		// Background
		p.background(0);

		// Translate lower left;
		p.scale(p.width / 640f);
		// p.scale(1.6f);

		// GUI
		gui.update();

		// draw depthImageMap
		// p.image(kinect.context().rgbImage(), 0, 0);

		// Draw Player
		if (DEBUG) {
			drawPlayer();
		}
	}

	private void drawPlayer() {

		for (Player player : kinect.getPlayers()) {
			p.pushStyle();
			p.fill(255, 0, 255);
			p.noStroke();
			p.ellipse(player.getHandLeft().x, player.getHandLeft().y, 40, 40);
			p.ellipse(player.getHandRight().x, player.getHandRight().y, 40, 40);
			p.popStyle();
		}

	}

}
