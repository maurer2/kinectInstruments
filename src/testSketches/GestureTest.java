package testSketches;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PMatrix3D;
import processing.core.PVector;
import SimpleOpenNI.*;

public class GestureTest extends PApplet {

	SimpleOpenNI context;

	// NITE
	XnVSessionManager sessionManager;
	XnVFlowRouter flowRouter;
	boolean handsTrackFlag = false;
	PVector handVec = new PVector();
	List<PVector> handVecList = new ArrayList();
	int handVecListSize = 30;
	String lastGesture = "";

	public void setup() {
		context = new SimpleOpenNI(this);

		// mirror is by default enabled
		context.setMirror(true);

		// enable depthMap generation
		if (context.enableDepth() == false) {
			println("Can't open the depthMap, maybe the camera is not connected!");
			exit();
			return;
		}

		// enable the hands + gesture
		context.enableGesture();
		context.enableHands();

		context.addGesture("Wave");
		// setup NITE
		// sessionManager = context.createSessionManager("Click,Wave",
		// "RaiseHand");

		size(context.depthWidth(), context.depthHeight());
		smooth();
	}

	public void draw() {
		background(200, 0, 0);
		// update the cam
		context.update();

		// update nite
		// context.update(sessionManager);

		// draw depthImageMap
		image(context.depthImage(), 0, 0);

		// draw the list
		// pointDrawer.draw();
	}

	public void keyPressed() {
		switch (key) {
		case 'e':
			// end sessions
			sessionManager.EndSession();
			println("end session");
			break;
		}
	}

	// -----------------------------------------------------------------
	// hand events

	void onCreateHands(int handId, PVector pos, float time) {
		println("onCreateHands - handId: " + handId + ", pos: " + pos + ", time:" + time);

		handsTrackFlag = true;
		handVec = pos;

		handVecList.clear();
		handVecList.add(pos);
	}

	void onUpdateHands(int handId, PVector pos, float time) {
		// println("onUpdateHandsCb - handId: " + handId + ", pos: " + pos +
		// ", time:" + time);
		handVec = pos;

		handVecList.add(0, pos);
		if (handVecList.size() >= handVecListSize) { // remove the last point
			handVecList.remove(handVecList.size() - 1);
		}
	}

	void onDestroyHands(int handId, float time) {
		println("onDestroyHandsCb - handId: " + handId + ", time:" + time);

		handsTrackFlag = false;
		context.addGesture(lastGesture);
	}

	// -----------------------------------------------------------------
	// gesture events

	void onRecognizeGesture(String strGesture, PVector idPosition, PVector endPosition) {
		println("onRecognizeGesture - strGesture: " + strGesture + ", idPosition: " + idPosition
				+ ", endPosition:" + endPosition);

		lastGesture = strGesture;
		context.removeGesture(strGesture);
		context.startTrackingHands(endPosition);

	}

	void onProgressGesture(String strGesture, PVector position, float progress) {
		// println("onProgressGesture - strGesture: " + strGesture +
		// ", position: " + position + ", progress:" + progress);
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////
	// session callbacks

	public void onStartSession(PVector pos) {
		println("onStartSession: " + pos);
	}

	public void onEndSession() {
		println("onEndSession: ");
	}

	public void onFocusSession(String strFocus, PVector pos, float progress) {
		println("onFocusSession: focus=" + strFocus + ",pos=" + pos + ",progress=" + progress);
	}

}