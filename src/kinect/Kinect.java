package kinect;

import processing.core.PApplet;
import SimpleOpenNI.SimpleOpenNI;

public class Kinect {
	private SimpleOpenNI context;
	private PApplet p;
	private boolean autoCalib = true;
	private boolean playRecording = true;

	public Kinect(PApplet p) {
		context = new SimpleOpenNI(p, SimpleOpenNI.RUN_MODE_MULTI_THREADED);

		// init Kinect
		initKinect();
	}

	private void initKinect() {
		if (playRecording) {
			context.openFileRecording("smite1.oni");
			context.seekPlayer(150, SimpleOpenNI.PLAYER_SEEK_CUR);
		}

		context.enableDepth();
		context.enableRGB();

		// Skelett anschalten --> THIS wichtig, da sonst keine Erkennung
		// auﬂerhalb Main
		context.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL, this);
		context.enableScene(640, 480, 60);
		context.setSmoothingHands(0.2f);
		context.setSmoothingSkeleton(0.2f);
		context.mirror();

		// enable depthMap generation
		if (context.enableDepth() == false) {
			p.println("Fehler Depth Map");
			p.exit();
			return;
		}
	}

	public SimpleOpenNI getKinect() {
		return context;
	}

	// SimpleOpenNI events

	public void onNewUser(int userId) {
		p.println("onNewUser - userId: " + userId);
		p.println("  start pose detection");

		if (autoCalib)
			context.requestCalibrationSkeleton(userId, true);
		else
			context.startPoseDetection("Psi", userId);
	}

	public void onLostUser(int userId) {
		p.println("onLostUser - userId: " + userId);
	}

	public void onExitUser(int userId) {
		p.println("onExitUser - userId: " + userId);
	}

	public void onReEnterUser(int userId) {
		p.println("onReEnterUser - userId: " + userId);
	}

	public void onStartCalibration(int userId) {
		p.println("onStartCalibration - userId: " + userId);
	}

	public void onEndCalibration(int userId, boolean successfull) {
		p.println("onEndCalibration - userId: " + userId + ", successfull: " + successfull);

		if (successfull) {
			p.println("  User calibrated !!!");
			context.startTrackingSkeleton(userId);
		} else {
			p.println("  Failed to calibrate user !!!");
			p.println("  Start pose detection");
			context.startPoseDetection("Psi", userId);
		}
	}

	public void onStartPose(String pose, int userId) {
		p.println("onStartPose - userId: " + userId + ", pose: " + pose);
		p.println(" stop pose detection");

		context.stopPoseDetection(userId);
		context.requestCalibrationSkeleton(userId, true);

	}

	public void onEndPose(String pose, int userId) {
		p.println("onEndPose - userId: " + userId + ", pose: " + pose);
	}

}
