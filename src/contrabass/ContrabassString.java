package contrabass;

import processing.core.PVector;

public class ContrabassString {
	public int id;

	private PVector vectorStart = new PVector();
	private PVector vectorEnd = new PVector();
	public PVector centerOfVector = new PVector();

	public float padding = 0;
	public float dotProduct = 1;
	public float dotProductLeft = 1;
	public float dotProductRight = 1;

	public ContrabassString(float padding, int id) {
		this.padding = padding;
		this.id = id;
	}

	public PVector rv(boolean normalized) {
		PVector rv = new PVector(vectorStart.x - centerOfVector.x, vectorStart.y - centerOfVector.y);

		if (normalized) {
			rv.normalize();
		}

		return rv;
	}

	public PVector ov(boolean normalized) {
		PVector ov = new PVector(vectorStart.y - centerOfVector.y, -vectorStart.x
				- centerOfVector.x);

		if (normalized) {
			ov.normalize();
		}

		return ov;
	}

	public PVector start() {
		return vectorStart;
	}

	public PVector end() {
		return vectorEnd;
	}

	public PVector center() {
		return centerOfVector;
	}

	public float distance() {
		float distance = vectorStart.dist(vectorEnd) / 2;
		return distance;
	}

	public PVector halfVektor() {
		PVector pv = new PVector(vectorEnd.x - vectorStart.x, vectorEnd.y - vectorStart.y);
		return pv;
	}

	public PVector orthogonalVector() {
		PVector pv = new PVector(-vectorEnd.y - vectorStart.y, vectorEnd.x - vectorStart.x);

		return pv;
	}

	public PVector directionVector() {
		PVector pv = new PVector(-vectorEnd.y - vectorStart.y, vectorEnd.x - vectorStart.x);

		return pv;
	}

}
