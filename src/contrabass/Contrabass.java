package contrabass;

import guitar.GuitarString;

import instruments.IKinectInstrument;

import java.util.ArrayList;
import java.util.List;

import player.Player;
import player.VectorHelper;
import processing.core.PApplet;
import processing.core.PVector;

import main.Main;
import midi.MidiMain;

public class Contrabass implements IKinectInstrument {
	List<ContrabassString> stringsList = new ArrayList<>();

	private float marginStrings;
	private float distanceNeck;
	private float distanceFred;
	private float numberOfNeckAreas;
	private Main p;
	private MidiMain midi;

	public boolean debug = false;

	public Contrabass(Main p, float numberStrings, float marginStrings, float distanceNeck,
			float distanceFred, int numberOfNeckAreas, MidiMain midi) {

		this.marginStrings = marginStrings;
		this.distanceNeck = distanceNeck;
		this.distanceFred = distanceFred;
		this.numberOfNeckAreas = numberOfNeckAreas;
		this.p = p;
		this.midi = midi;

		// Generate Strings
		generateStrings(numberStrings);
	}

	private void generateStrings(float numberOfStrings) {
		stringsList.clear();
		if (numberOfStrings < 1)
			return;
		float padding = -(numberOfStrings - 1) / 2;
		for (int i = 0; i < numberOfStrings; i++) {
			stringsList.add(new ContrabassString(padding, i));
			padding += 1;
		}
	}

	public void update(Player player) {
		// Ausgangsvektoren
		PVector v = player.getHandRight();

		// Richtungsvektor zu punkt 1 aka Linke Hand
		PVector rv = new PVector(v.x - player.getTorso().x, v.y - player.getTorso().y);
		// PVector rv = new PVector(v.x - player.getWaiste(false).x, v.y -
		// player.getWaiste(false).y);
		rv.normalize();

		// Ortsvektor -> Orthogonal zu RV
		PVector ov = VectorHelper.orthogonalVector(rv, true);

		// Position des Necks
		PVector neckPos = rv.get();
		neckPos.mult(distanceNeck);

		// Position des Frets
		PVector fredPos = rv.get();
		fredPos.mult(-distanceFred);

		for (ContrabassString myString : stringsList) {

			// Verschiebungsvektor vom COM
			PVector translation = ov.get();
			translation.mult(myString.padding * marginStrings);

			// Neuer COM des Vektors
			myString.centerOfVector = translation.get();

			// Start und Ende verschieben
			myString.start().set(neckPos);
			myString.start().add(translation);
			myString.end().set(fredPos);
			myString.end().add(translation);
		}

		// Draw()
		draw(player);

		// Check Match
		checkFredMatch(player);
	}

	private void checkFredMatch(Player player) {
		PVector v = player.getHandLeftAbsolute();
		v.normalize();

		for (ContrabassString myString : stringsList) {

			// Vektor von Center of Vector zu Ende/Start
			PVector rv = new PVector(myString.start().x - myString.centerOfVector.x,
					myString.start().y - myString.centerOfVector.y);
			rv.normalize();

			// Orthogonaler Vektor zum RV2
			PVector ov = VectorHelper.orthogonalVector(rv, true);

			// Testarea Stuff
			int testArea = 30;
			PVector testVectorTop = myString.centerOfVector.get();
			PVector testVectorBottom = myString.centerOfVector.get();

			testVectorTop.normalize();
			testVectorBottom.normalize();

			testVectorTop.mult(testArea);
			testVectorBottom.mult(-testArea);

			testVectorTop.add(myString.centerOfVector.get());
			testVectorBottom.add(myString.centerOfVector.get());

			// Check Neckpostion
			int neckValue = checkNeckMatch(player);

			// Dot Product
			float dotProduct = v.dot(ov);

			// Crap
			if (myString.dotProduct < 0 && dotProduct > 0) {
				float handDistance = v.dist(ov);

				System.out.println("hit up - Neck" + neckValue + " " + handDistance);
				midi.playMidiContrabass(myString.id, neckValue, true, handDistance, 3);

			} else if (myString.dotProduct > 0 && dotProduct < 0) {
				float handDistance = v.dist(ov);

				midi.playMidiContrabass(myString.id, neckValue, false, handDistance, 3);
				System.out.println("hit down- Neck" + neckValue + " " + handDistance);
			}

			// Neues Dot Product Speichern
			myString.dotProduct = dotProduct;
		}
	}

	private int checkNeckMatch(Player player) {
		PVector v = player.getHandLeftAbsolute();

		float neckValue = 0;

		for (ContrabassString myString : stringsList) {
			PVector rv2 = new PVector(myString.centerOfVector.x, myString.centerOfVector.y);

			PVector endVector = new PVector(myString.start().x - myString.centerOfVector.x,
					myString.start().y - myString.centerOfVector.y);

			// Distance Center of Vector Hand
			float distance = v.dist(myString.centerOfVector);

			// Limit Max Distance
			if (distance >= distanceNeck) {
				// v1.set(endVector);
			}

			// Mapping
			float mappedValue = PApplet.map(distance, 0, distanceNeck, numberOfNeckAreas, 0);
			// System.out.println(Math.round(mappedValue));

			if (mappedValue < 0) {
				mappedValue = 0;
			}

			neckValue = mappedValue;
		}

		return Math.round(neckValue);
	}

	private void draw(Player player) {
		p.pushStyle();
		p.stroke(255, 0, 255);
		p.strokeWeight(4);

		// Translate zum COM
		p.translate(player.getTorso().x, player.getTorso().y);

		for (ContrabassString myString : stringsList) {

			p.line(myString.start().x, myString.start().y, myString.end().x, myString.end().y);
		}

		p.popStyle();
	}

}
