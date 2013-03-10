package screen;

import processing.core.PVector;
import kinect.Kinect;
import SimpleOpenNI.SimpleOpenNI;
import main.IUpdate;
import main.Main;

public class Screen implements IUpdate {
	private Main p;
	private Kinect kinect;

	public Screen(Main p, Kinect kinect) {
		this.p = p;
		this.kinect = kinect;
	}

	public void update() {
		// Background
		p.background(0);

		// draw depthImageMap
		p.image(kinect.context().depthImage(), 0, 0);

		int[] userList = kinect.context().getUsers();

		// draw the skeleton if it's available

		for (int i = 0; i < userList.length; i++) {
			if (kinect.context().isTrackingSkeleton(userList[i]))
				drawSkeleton(userList[i]);
		}

	}

	// draw the skeleton with the selected joints
	private void drawSkeleton(int userId) {
		PVector com = new PVector();
		kinect.context().getCoM(userId, com);
		kinect.context().convertRealWorldToProjective(com, com);
		p.ellipse(com.x, com.y, 20, 20);

	}

}
