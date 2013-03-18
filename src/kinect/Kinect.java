package kinect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.Main;
import player.Player;
import player.PlayersUpdater;
import SimpleOpenNI.SimpleOpenNI;

public class Kinect {
	private SimpleOpenNI context;
	private Main p;
	private boolean autoCalib = true;
	private boolean playRecording = false;
	private PlayersUpdater playersUpdater;
	private HashMap<Integer, Player> playersList;

	public boolean kinectReady = false;

	public Kinect(Main p, boolean playRecording) {
		context = new SimpleOpenNI(p, SimpleOpenNI.RUN_MODE_MULTI_THREADED);
		playersList = new HashMap<>();
		playersUpdater = new PlayersUpdater(context, playersList);
		this.playRecording = playRecording;

		// init Kinect
		initKinect();
	}

	private void initKinect() {
		if (playRecording) {
			context.openFileRecording("contrabass.oni");
			context.seekPlayer(150, SimpleOpenNI.PLAYER_SEEK_CUR);
		}

		context.enableDepth();
		context.enableRGB();

		// Skelett --> THIS wichtig, da sonst keine Erkennung auﬂerhalb Main
		context.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL, this);
		context.enableScene(640, 480, 60);
		context.setSmoothingHands(0.2f);
		context.setSmoothingSkeleton(0.2f);
		// context.setMirror(true);

	}

	public void update() {
		// Kinect update
		context.update();

		if (kinectReady == false)
			return;

		// Userdaten update
		// updateUserCoordindates();
		playersUpdater.update();

	}

	public SimpleOpenNI context() {
		return context;
	}

	public List<Player> getPlayers() {
		List<Player> list = new ArrayList<Player>(playersList.values());
		return list;
	}

	// SimpleOpenNI events

	public void onNewUser(int userId) {
		Main.println("onNewUser - userId: " + userId);
		Main.println("start pose detection");

		if (autoCalib)
			context.requestCalibrationSkeleton(userId, true);
		else
			context.startPoseDetection("Psi", userId);
	}

	public void onLostUser(int userId) {
		Main.println("onLostUser - userId: " + userId);
		playersList.remove(userId);
	}

	public void onExitUser(int userId) {
		Main.println("onExitUser - userId: " + userId);
	}

	public void onReEnterUser(int userId) {
		Main.println("onReEnterUser - userId: " + userId);
	}

	public void onStartCalibration(int userId) {
		Main.println("onStartCalibration - userId: " + userId);
	}

	public void onEndCalibration(int userId, boolean successfull) {
		Main.println("onEndCalibration - userId: " + userId + ", successfull: " + successfull);

		if (successfull) {
			Main.println("User calibrated !!!");
			context.startTrackingSkeleton(userId);

			kinectReady = true;

			// Add Player to List
			playersList.put(userId, new Player(userId));

		} else {
			Main.println("Failed to calibrate user !!!");
			Main.println("Start pose detection");
			context.startPoseDetection("Psi", userId);
		}
	}

	public void onStartPose(String pose, int userId) {
		Main.println("onStartPose - userId: " + userId + ", pose: " + pose);
		Main.println("stop pose detection");

		context.stopPoseDetection(userId);
		context.requestCalibrationSkeleton(userId, true);
	}

	public void onEndPose(String pose, int userId) {
		Main.println("onEndPose - userId: " + userId + ", pose: " + pose);
	}
}
