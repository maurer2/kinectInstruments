package player;

import processing.core.PVector;

public class LimbVector extends PVector {
	public float confidence = 0;

	private float acceleration;
	private float velocity;
	private float accelerationLastFrame;
	private float velocityLastFrame;

	private PVector positionLastFrame;

	public LimbVector() {
		super();
	}

}
