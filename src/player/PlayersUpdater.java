package player;

import java.util.HashMap;

import SimpleOpenNI.SimpleOpenNI;

public class PlayersUpdater {
	private SimpleOpenNI context;
	private HashMap<Integer, Player> playersList;

	public PlayersUpdater(SimpleOpenNI context, HashMap<Integer, Player> playersList) {
		this.context = context;
		this.playersList = playersList;
	}

	public void update() {
		int[] userList = context.getUsers();
		for (int i = 0; i < userList.length; i++) {
			if (context.isTrackingSkeleton(userList[i])) {

				// New Vectors
				LimbVector handLeft = new LimbVector();
				LimbVector handRight = new LimbVector();

				LimbVector head = new LimbVector();
				LimbVector neck = new LimbVector();

				LimbVector shoulderLeft = new LimbVector();
				LimbVector shoulderRight = new LimbVector();

				LimbVector elbowLeft = new LimbVector();
				LimbVector elbowRight = new LimbVector();

				LimbVector torso = new LimbVector();
				LimbVector centerOfMass = new LimbVector();

				LimbVector hipLeft = new LimbVector();
				LimbVector hipRight = new LimbVector();

				// Get Positions
				handLeft.confidence = context.getJointPositionSkeleton(userList[i],
						SimpleOpenNI.SKEL_LEFT_HAND, handLeft);
				handRight.confidence = context.getJointPositionSkeleton(userList[i],
						SimpleOpenNI.SKEL_RIGHT_HAND, handRight);

				head.confidence = context.getJointPositionSkeleton(userList[i],
						SimpleOpenNI.SKEL_HEAD, head);
				neck.confidence = context.getJointPositionSkeleton(userList[i],
						SimpleOpenNI.SKEL_NECK, neck);

				shoulderLeft.confidence = context.getJointPositionSkeleton(userList[i],
						SimpleOpenNI.SKEL_LEFT_SHOULDER, shoulderLeft);
				shoulderRight.confidence = context.getJointPositionSkeleton(userList[i],
						SimpleOpenNI.SKEL_RIGHT_SHOULDER, shoulderRight);

				elbowLeft.confidence = context.getJointPositionSkeleton(userList[i],
						SimpleOpenNI.SKEL_LEFT_ELBOW, elbowLeft);
				elbowRight.confidence = context.getJointPositionSkeleton(userList[i],
						SimpleOpenNI.SKEL_RIGHT_ELBOW, elbowRight);

				torso.confidence = context.getJointPositionSkeleton(userList[i],
						SimpleOpenNI.SKEL_TORSO, torso);

				hipLeft.confidence = context.getJointPositionSkeleton(userList[i],
						SimpleOpenNI.SKEL_LEFT_HIP, hipLeft);
				hipRight.confidence = context.getJointPositionSkeleton(userList[i],
						SimpleOpenNI.SKEL_RIGHT_HIP, hipRight);

				boolean comConfidence = context.getCoM(userList[i], centerOfMass);
				if (comConfidence) {
					centerOfMass.confidence = 1;
				}

				// Convert
				context.convertRealWorldToProjective(handLeft, handLeft);
				context.convertRealWorldToProjective(handRight, handRight);

				context.convertRealWorldToProjective(head, head);
				context.convertRealWorldToProjective(neck, neck);

				context.convertRealWorldToProjective(shoulderLeft, shoulderLeft);
				context.convertRealWorldToProjective(shoulderRight, shoulderRight);

				context.convertRealWorldToProjective(elbowLeft, elbowLeft);
				context.convertRealWorldToProjective(elbowRight, elbowRight);

				context.convertRealWorldToProjective(torso, torso);
				context.convertRealWorldToProjective(centerOfMass, centerOfMass);

				context.convertRealWorldToProjective(hipLeft, hipLeft);
				context.convertRealWorldToProjective(hipRight, hipRight);

				// Set Player Coordinates
				playersList.get(userList[i]).setHandLeft(handLeft);
				playersList.get(userList[i]).setHandRight(handRight);

				playersList.get(userList[i]).setHead(head);
				playersList.get(userList[i]).setNeck(neck);

				playersList.get(userList[i]).setShoulderLeft(shoulderLeft);
				playersList.get(userList[i]).setShoulderRight(shoulderRight);

				playersList.get(userList[i]).setElbowLeft(elbowLeft);
				playersList.get(userList[i]).setElbowRight(elbowRight);

				playersList.get(userList[i]).setTorso(torso);
				playersList.get(userList[i]).setCenterOfMass(centerOfMass);

				playersList.get(userList[i]).setHipLeft(hipLeft);
				playersList.get(userList[i]).setHipRight(hipRight);
			}
		}

	}
}
