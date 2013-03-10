package screen;

import kinect.Kinect;
import SimpleOpenNI.SimpleOpenNI;
import main.Main;

public class Screen {
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
		kinect.context().drawLimb(userId, SimpleOpenNI.SKEL_HEAD, SimpleOpenNI.SKEL_NECK);

		kinect.context().drawLimb(userId, SimpleOpenNI.SKEL_NECK, SimpleOpenNI.SKEL_LEFT_SHOULDER);
		kinect.context().drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		kinect.context()
				.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_ELBOW, SimpleOpenNI.SKEL_LEFT_HAND);

		kinect.context().drawLimb(userId, SimpleOpenNI.SKEL_NECK, SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		kinect.context().drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		kinect.context().drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_ELBOW,
				SimpleOpenNI.SKEL_RIGHT_HAND);

		kinect.context().drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER, SimpleOpenNI.SKEL_TORSO);
		kinect.context()
				.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER, SimpleOpenNI.SKEL_TORSO);

		kinect.context().drawLimb(userId, SimpleOpenNI.SKEL_TORSO, SimpleOpenNI.SKEL_LEFT_HIP);
		kinect.context().drawLimb(userId, SimpleOpenNI.SKEL_LEFT_HIP, SimpleOpenNI.SKEL_LEFT_KNEE);
		kinect.context().drawLimb(userId, SimpleOpenNI.SKEL_LEFT_KNEE, SimpleOpenNI.SKEL_LEFT_FOOT);

		kinect.context().drawLimb(userId, SimpleOpenNI.SKEL_TORSO, SimpleOpenNI.SKEL_RIGHT_HIP);
		kinect.context()
				.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_HIP, SimpleOpenNI.SKEL_RIGHT_KNEE);
		kinect.context().drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_KNEE,
				SimpleOpenNI.SKEL_RIGHT_FOOT);
	}

}
