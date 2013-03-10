package player;

import java.util.Queue;

import processing.core.PVector;

public class Player {
	private int id = -1;

	private PVector handLeft = new LimbVector();
	private PVector handRight = new LimbVector();
	
	private PVector elbowLeft = new PVector();
	private PVector elbowRight = new PVector();
	private PVector shoulderLeft = new PVector();
	private PVector shoulderRight = new PVector();
	private PVector head = new PVector();
	private PVector torso = new PVector();
	private PVector neck = new PVector();
	private PVector centerOfMass = new PVector();

	private Queue<PVector> GuitarStrum;
	private Queue<PVector> GuitarHead;	

	public Player(int id) {
		super();
		this.id = id;		
	}

	public Player(int id, PVector handLeft, PVector handRight, PVector centerOfMass) {
		super();
		this.id = id;
		this.handLeft = handLeft;
		this.handRight = handRight;
		this.centerOfMass = centerOfMass;
	}

	public PVector getHandLeft() {
		return handLeft;
	}

	public void setHandLeft(PVector handLeft) {
		this.handLeft = handLeft;		
	}

	public PVector getHandRight() {
		return handRight;
	}

	public void setHandRight(PVector handRight) {
		this.handRight = handRight;
	}
	
	

}
