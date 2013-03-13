package player;

import java.util.Queue;

import processing.core.PVector;

public class Player {
	private int id = -1;
	private LimbVector head = new LimbVector();
	private LimbVector neck = new LimbVector();

	private LimbVector shoulderLeft = new LimbVector();
	private LimbVector shoulderRight = new LimbVector();

	private LimbVector elbowLeft = new LimbVector();
	private LimbVector elbowRight = new LimbVector();

	private LimbVector handLeft = new LimbVector();
	private LimbVector handRight = new LimbVector();

	private LimbVector torso = new LimbVector();
	private LimbVector centerOfMass = new LimbVector();

	private LimbVector hipLeft = new LimbVector();
	private LimbVector hipRight = new LimbVector();

	private Queue<LimbVector> GuitarStrum;
	private Queue<LimbVector> GuitarHead;

	public Player(int id) {
		super();
		this.id = id;
	}

	// Custom Vector
	public PVector getNeckToTorso(boolean normalized) {
		PVector v = new PVector(neck.x - torso.x, neck.y - torso.y);

		if (normalized) {
			v.normalize();
		}
		return v;
	}

	public PVector getHandLeftAbsolute() {
		PVector v = new PVector(handLeft.x - torso.x, handLeft.y - torso.y);	
		return v;
	}

	public PVector getHandRightAbsolute() {
		PVector v = new PVector(handRight.x - torso.x, handRight.y - torso.y);
		return v;
	}

	// Getter & Setter

	public LimbVector getHandLeft() {
		return handLeft;
	}

	public void setHandLeft(LimbVector handLeft) {
		this.handLeft = handLeft;
	}

	public LimbVector getHandRight() {
		return handRight;
	}

	public void setHandRight(LimbVector handRight) {
		this.handRight = handRight;
	}

	public LimbVector getHead() {
		return head;
	}

	public void setHead(LimbVector head) {
		this.head = head;
	}

	public LimbVector getNeck() {
		return neck;
	}

	public void setNeck(LimbVector neck) {
		this.neck = neck;
	}

	public LimbVector getShoulderLeft() {
		return shoulderLeft;
	}

	public void setShoulderLeft(LimbVector shoulderLeft) {
		this.shoulderLeft = shoulderLeft;
	}

	public LimbVector getShoulderRight() {
		return shoulderRight;
	}

	public void setShoulderRight(LimbVector shoulderRight) {
		this.shoulderRight = shoulderRight;
	}

	public LimbVector getElbowLeft() {
		return elbowLeft;
	}

	public void setElbowLeft(LimbVector elbowLeft) {
		this.elbowLeft = elbowLeft;
	}

	public LimbVector getElbowRight() {
		return elbowRight;
	}

	public void setElbowRight(LimbVector elbowRight) {
		this.elbowRight = elbowRight;
	}

	public LimbVector getTorso() {
		return torso;
	}

	public void setTorso(LimbVector torso) {
		this.torso = torso;
	}

	public LimbVector getCenterOfMass() {
		return centerOfMass;
	}

	public void setCenterOfMass(LimbVector centerOfMass) {
		this.centerOfMass = centerOfMass;
	}

	public LimbVector getHipLeft() {
		return hipLeft;
	}

	public void setHipLeft(LimbVector hipLeft) {
		this.hipLeft = hipLeft;
	}

	public LimbVector getHipRight() {
		return hipRight;
	}

	public void setHipRight(LimbVector hipRight) {
		this.hipRight = hipRight;
	}

}
