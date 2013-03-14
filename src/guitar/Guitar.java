package guitar;

import instruments.IKinectInstrument;

import java.util.ArrayList;
import java.util.List;

import player.Player;
import processing.core.PApplet;
import processing.core.PVector;

public class Guitar implements IKinectInstrument {
	List<GuitarString> _myStrings = new ArrayList<>();

	private float _myNumbrOfStrings; // Anzahl Saiten
	private float _myStringSpace; // Abstand Saiten
	private float _myNeckDistance; // Abstand COM - Head
	private float _myFredDistance; // Abstand COM - Tail
	private float numberOfNeckAreas;

	public PApplet p;
	public boolean debug = true;

	private boolean fredValue = false;

	public Guitar(float _myNumbrOfStrings, float _myStringSpace, float _myNeckDistance,
			float _myFredDistance, PApplet p, int numberOfNeckAreas) {
		super();
		this._myNumbrOfStrings = _myNumbrOfStrings;
		this._myStringSpace = _myStringSpace;
		this._myNeckDistance = _myNeckDistance;
		this._myFredDistance = _myFredDistance;

		this.p = p;

		this.numberOfNeckAreas = numberOfNeckAreas;

		generateStrings(_myNumbrOfStrings);
	}

	private void generateStrings(float numberOfStrings) {
		_myStrings.clear();
		if (numberOfStrings < 1)
			return;
		float padding = -(numberOfStrings - 1) / 2;
		for (int i = 0; i < numberOfStrings; i++) {
			_myStrings.add(new GuitarString(padding, i));
			padding += 1;

			// System.out.println(padding);
		}
	}

	public void update(Player player) {
		// Ausgangsvektoren
		PVector v1 = player.getHandLeft().get();

		// Richtungsvektor zu punkt 1 aka Linke Hand
		PVector rv = new PVector(v1.x - player.getTorso().x, v1.y - player.getTorso().y);
		rv.normalize();

		// Ortsvektor -> Orthogonal zu RV
		PVector ov = new PVector(rv.y, -rv.x);
		ov.normalize();

		// Position des Necks
		PVector neckPos = new PVector(rv.x, rv.y);
		neckPos.mult(_myNeckDistance);

		// Position des Freds
		PVector fredPos = new PVector(rv.x, rv.y);
		fredPos.mult(-_myFredDistance);

		for (GuitarString myString : _myStrings) {

			// Verschiebungsvektor vom COM
			PVector translation = new PVector(ov.x, ov.y);
			translation.mult(myString.padding * _myStringSpace);

			// Neuer COM des Vektors
			myString.centerOfVector = translation.get();

			// Start und Ende verschieben
			myString.start().set(neckPos);
			myString.start().add(translation);
			myString.end().set(fredPos);
			myString.end().add(translation);
		}

	}

	public void checkFredMatch(Player player) {
		PVector v2 = player.getHandRightAbsolute().get();
		v2.normalize();

		for (GuitarString myString : _myStrings) {
			// Vektor von Center of Vector zu Ende/Start
			PVector rv2 = new PVector(myString.start().x - myString.centerOfVector.x,
					myString.start().y - myString.centerOfVector.y);
			rv2.normalize();

			// Orthogonaler Vektor zum RV2
			PVector ov2 = new PVector(rv2.y, -rv2.x);
			ov2.normalize();
			// ov2.mult(60f);

			// frickity frack
			int testArea = 30;
			PVector testVectorTop = myString.centerOfVector.get();
			PVector testVectorBottom = myString.centerOfVector.get();

			testVectorTop.normalize();
			testVectorBottom.normalize();

			testVectorTop.mult(testArea);
			testVectorBottom.mult(-testArea);

			testVectorTop.add(myString.centerOfVector.get());
			testVectorBottom.add(myString.centerOfVector.get());

			if (debug) {
				p.ellipse(testVectorTop.x, testVectorTop.y, 10, 10);
				p.ellipse(testVectorBottom.x, testVectorBottom.y, 10, 10);
				p.line(testVectorTop.x, testVectorTop.y, testVectorBottom.x, testVectorBottom.y);
			}

			testVectorTop.normalize();
			testVectorBottom.normalize();

			PVector tempCenter = myString.centerOfVector.get();
			tempCenter.normalize();

			// Check Neckpostion
			int neckValue = checkNeckMatch(player);

			float dotProduct = v2.dot(ov2);

			// Crap
			if (myString.dotProduct < 0 && dotProduct > 0) {
				// System.out.println("pass");
				fredValue = true;
				System.out.println("hit up - Neck" + neckValue);
				// midi.playMidi(myString.id, neckValue, true);

			} else if (myString.dotProduct > 0 && dotProduct < 0) {
				fredValue = true;
				// midi.playMidi(myString.id, neckValue, false);
				System.out.println("hit down- Neck" + neckValue);
			}

			// Neues Dot Product Speichern
			myString.dotProduct = dotProduct;

			if (fredValue) {
				// midi.playMidi(myString.id, neckValue);
			}
		}
	}

	public int checkNeckMatch(Player player) {
		// PVector v1 = player.handLeft.get();
		PVector v1 = player.getHandLeftAbsolute().get();
		// v1.normalize();

		// PVector rv = new PVector(v1.x - player.centerOfMass.x, v1.y -
		// player.centerOfMass.y);
		// rv.normalize();

		float neckValue = 0;

		for (GuitarString myString : _myStrings) {
			PVector rv2 = new PVector(myString.centerOfVector.x, myString.centerOfVector.y);

			PVector endVector = new PVector(myString.start().x - myString.centerOfVector.x,
					myString.start().y - myString.centerOfVector.y);

			// Distance Center of Vector Hand
			float distance = v1.dist(myString.centerOfVector);

			// Limit Max Distance
			if (distance >= _myNeckDistance) {
				// v1.set(endVector);
			}

			// Mapping
			float mappedValue = PApplet.map(distance, 0, _myNeckDistance, numberOfNeckAreas, 0);
			// System.out.println(Math.round(mappedValue));

			if (mappedValue < 0)
				mappedValue = 0;
			neckValue = mappedValue;

			// Player Hand
			// p.ellipse(v1.x, v1.y, 10, 10);

			// COM des Vektors zu Hand
			// p.line(v1.x, v1.y, myString.centerOfVector.x,
			// myString.centerOfVector.y);

			// Endvektor
			// p.ellipse(endVector.x, endVector.y, 10, 10);

		}

		return Math.round(neckValue);
	}

	public void checkHeadFred() {
		// midi.playMidi(myString.id);
		// System.out.println("Fred " + fredValue);
		// System.out.println("Neck " + Math.round(neckValue));
	}

	public void draw(Player player) {
		p.stroke(255, 0, 255);
		p.strokeWeight(2);

		for (GuitarString myString : _myStrings) {
			p.stroke(0, 255, 255);
			p.line(myString.start().x, myString.start().y, myString.end().x, myString.end().y);
		}

		// Draw Player
		// p.ellipse(player.getHandLeftAbsolute().x,player.getHandLeftAbsolute().y,
		// 10, 10);
		// p.ellipse(player.getHandRightAbsolute().x,
		// player.getHandRightAbsolute().y, 10, 10);
	}

}