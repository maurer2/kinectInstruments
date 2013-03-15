package guitar;

import instruments.IKinectInstrument;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import player.Player;
import processing.core.PApplet;
import processing.core.PVector;

public class Guitar implements IKinectInstrument {
	List<GuitarString> stringsList = new ArrayList<>();

	private float marginStrings;
	private float distanceNeck;
	private float distanceFred;
	private float numberOfNeckAreas;
	private boolean fredValue = false;
	private Main p;

	public boolean debug = true;

	public Guitar(Main p, float numberStrings, float marginStrings, float distanceNeck,
			float distanceFred, int numberOfNeckAreas) {

		this.marginStrings = marginStrings;
		this.distanceNeck = distanceNeck;
		this.distanceFred = distanceFred;
		this.numberOfNeckAreas = numberOfNeckAreas;
		this.p = p;

		// Generate Strings
		generateStrings(numberStrings);
	}

	private void generateStrings(float numberOfStrings) {
		stringsList.clear();
		if (numberOfStrings < 1)
			return;
		float padding = -(numberOfStrings - 1) / 2;
		for (int i = 0; i < numberOfStrings; i++) {
			stringsList.add(new GuitarString(padding, i));
			padding += 1;
		}
	}

	public void update(Player player) {
		// Ausgangsvektoren
		PVector v1 = player.getHandRight().get();

		// Richtungsvektor zu punkt 1 aka Linke Hand
		PVector rv = new PVector(v1.x - player.getTorso().x, v1.y - player.getTorso().y);
		rv.normalize();

		// Ortsvektor -> Orthogonal zu RV
		PVector ov = new PVector(rv.y, -rv.x);
		ov.normalize();

		// Position des Necks
		PVector neckPos = new PVector(rv.x, rv.y);
		neckPos.mult(distanceNeck);

		// Position des Freds
		PVector fredPos = new PVector(rv.x, rv.y);
		fredPos.mult(-distanceFred);

		for (GuitarString myString : stringsList) {

			// Verschiebungsvektor vom COM
			PVector translation = new PVector(ov.x, ov.y);
			translation.mult(myString.padding * marginStrings);

			// Neuer COM des Vektors
			myString.centerOfVector = translation.get();

			// Start und Ende verschieben
			myString.start().set(neckPos);
			myString.start().add(translation);
			myString.end().set(fredPos);
			myString.end().add(translation);

			p.ellipse(myString.start().x, myString.start().y, 10, 10);
			p.ellipse(myString.end().x, myString.end().y, 10, 10);

			p.ellipse(myString.center().x, myString.center().y, 10, 10);
		}

		draw(player);

	}

	public void checkFredMatch(Player player) {
		PVector v2 = player.getHandLeft().get();
		v2.normalize();

		for (GuitarString myString : stringsList) {
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

		for (GuitarString myString : stringsList) {
			PVector rv2 = new PVector(myString.centerOfVector.x, myString.centerOfVector.y);

			PVector endVector = new PVector(myString.start().x - myString.centerOfVector.x,
					myString.start().y - myString.centerOfVector.y);

			// Distance Center of Vector Hand
			float distance = v1.dist(myString.centerOfVector);

			// Limit Max Distance
			if (distance >= distanceNeck) {
				// v1.set(endVector);
			}

			// Mapping
			float mappedValue = PApplet.map(distance, 0, distanceNeck, numberOfNeckAreas, 0);
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

		// Translate zum COM
		p.translate(player.getTorso().x, player.getTorso().y);

		for (GuitarString myString : stringsList) {

			p.line(myString.start().x, myString.start().y, myString.end().x, myString.end().y);
		}

		// Draw Player
		// p.ellipse(player.getHandLeftAbsolute().x,player.getHandLeftAbsolute().y,
		// 10, 10);
		// p.ellipse(player.getHandRightAbsolute().x,
		// player.getHandRightAbsolute().y, 10, 10);
	}

}