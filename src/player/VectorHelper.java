package player;

import processing.core.PVector;

public class VectorHelper {

	public static PVector orthogonalVector(PVector v, boolean normalized) {
		PVector o = new PVector(v.y, -v.x);

		if (normalized) {
			o.normalize();
		}

		return o;
	}

}
