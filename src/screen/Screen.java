package screen;

import java.util.List;

import kinect.Kinect;
import main.IUpdate;
import main.Main;
import player.Player;
import processing.core.PVector;

public class Screen implements IUpdate {
	private Main p;
	private Kinect kinect;

	private boolean DEBUG = true;

	public Screen(Main p, Kinect kinect) {
		this.p = p;
		this.kinect = kinect;
	}

	public void update() {
		// Background
		p.background(0);

		// draw depthImageMap
		p.image(kinect.context().depthImage(), 0, 0);

		// Draw Player
		if (DEBUG) {
			drawPlayer();
		}

	}

	private void drawPlayer() {

		for (Player player : kinect.getPlayers()) {
			// System.out.println(player.getHandLeft());
			PVector handLeft = player.getHandLeft();
			p.ellipse(handLeft.x, handLeft.y, 20, 20);

		}

	}

}
