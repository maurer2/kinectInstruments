package player;

import processing.core.PVector;

public class HitDetection {
	private float angle;
	private float velocity;
	private float acceleration;

	private float angleLastFrame;
	private float velocityLastFrame;
	private float accelerationLastFrame;

	private float thresholdVelocity = 20;
	private float thresholdCounter = 5;
	private float counter;

	public void update(PVector v1, PVector v2) {
		float newAngle = (float) Math.toDegrees(Math.acos(v1.dot(v2)));

		if (newAngle != angle) {
			angleLastFrame = angle;
			angle = newAngle;

			velocityLastFrame = velocity;
			velocity = angle - angleLastFrame;

			accelerationLastFrame = acceleration;
			acceleration = velocity - velocityLastFrame;

			if (counter >= thresholdCounter) {
				detectHit();
			}
			counter++;
		}

	}

	private void detectHit() {
		if (velocityLastFrame > 0 && Math.abs(velocityLastFrame) > thresholdVelocity) {

			// System.out.println("Hit");
			counter = 0;
		}
	}

	public float getAngle() {
		return angle;
	}

	public float getVelocity() {
		return velocity;
	}

	public float getAcceleration() {
		return acceleration;
	}

}
