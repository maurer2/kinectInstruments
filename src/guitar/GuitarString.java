package guitar;

import processing.core.PVector;

public class GuitarString {
	public int id;

	private PVector vectorStart = new PVector();
	private PVector vectorEnd = new PVector();
	private PVector vectorCenter = new PVector();
	public PVector centerOfVector; 

	public float padding = 0;
	public float dotProduct = 1;
	public float dotProductLeft = 1;
	public float dotProductRight = 1;

	public GuitarString(float padding, int id) {
		this.padding = padding;
		this.id = id;
	}

	public PVector rv(boolean normalized) {
		PVector rv = new PVector(vectorStart.x - vectorCenter.x, vectorStart.y - vectorCenter.y);

		if (normalized) {
			rv.normalize();
		}

		return rv;
	}

	public PVector ov(boolean normalized) {
		PVector ov = new PVector(vectorStart.y - vectorCenter.y, -vectorStart.x - vectorCenter.x);

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
		return vectorCenter;
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
		// pv.normalize();
		// PVector rv = new
		// PVector(vectorEnde.x-vectorStart.x,vectorEnde.y-vectorStart.y);

		return pv;
	}

	public PVector directionVector() {
		PVector pv = new PVector(-vectorEnd.y - vectorStart.y, vectorEnd.x - vectorStart.x);
		// pv.normalize();
		// PVector rv = new
		// PVector(vectorEnde.x-vectorStart.x,vectorEnde.y-vectorStart.y);

		return pv;
	}

}
