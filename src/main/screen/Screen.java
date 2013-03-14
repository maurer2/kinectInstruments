package main.screen;

import gui.OverlayGUI;

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
	private OverlayGUI gui;

	private boolean DEBUG = true;

	public Screen(Main p, Kinect kinect) {
		this.p = p;
		this.kinect = kinect;
		this.kinectStage = p.createGraphics(kinect.context().sceneWidth(), kinect.context()
				.sceneHeight());

		// GUI
		gui = new OverlayGUI(p);
	}

	public void update() {
		// Background
		p.background(0);

		// Translate lower left;
		p.scale(p.width / 640f);
		//p.scale(1.6f);

		// draw depthImageMap
		p.image(kinect.context().rgbImage(), 0, 0);

		// GUI
		gui.update();

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
