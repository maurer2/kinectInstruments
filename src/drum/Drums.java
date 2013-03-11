package drum;

import instruments.IKinectInstrument;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import player.Player;
import processing.core.PVector;

public class Drums implements IKinectInstrument {
	private Main p;
	private int size = 20;

	List<DrumSingle> _myDrums = new ArrayList<>();

	private float _myNumberOfDrums; // Anzahl Drums
	private float _myDrumSpace; // Abstand Drums
	private float _myDrumWidth; // Abstand COM - Tail
	private float _myDrumHeight; // Abstand COM - Tail

	public boolean debug = true;

	// private Midi midi;

	public Drums(Main p, float _myNumberOfDrums, float _myDrumSpace, float _myDrumWidth,
			float myDrumHeight) {
		this.p = p;

		this._myNumberOfDrums = _myNumberOfDrums;
		this._myDrumSpace = _myDrumSpace + _myDrumWidth;
		this._myDrumWidth = _myDrumWidth;
		this._myDrumHeight = myDrumHeight;

		// Drums erstellen
		generateDrums(_myNumberOfDrums);

		System.out.println("Drums loaded");
	}

	private void generateDrums(float _myNumbrOfDrums) {
		_myDrums.clear();
		if (_myNumbrOfDrums < 1)
			return;
		float padding = -(_myNumbrOfDrums - 1) / 2;
		for (int i = 0; i < _myNumbrOfDrums; i++) {
			_myDrums.add(new DrumSingle(padding, i));
			padding += 1;
		}
	}

	@Override
	public void update(Player player) {

		PVector v3 = player.getNeck().get();

		// Richtungsvektor zu Neck
		PVector rv = new PVector(v3.x - player.getCenterOfMass().x, v3.y
				- player.getCenterOfMass().y);
		rv.normalize();

		// Ortsvektor -> Orthogonal zu RV
		PVector ov = new PVector(rv.y, -rv.x);
		ov.normalize();

		// ov = new PVector(-1f,0);

		// Position des Necks
		PVector neckPos = ov.get();
		neckPos.mult(_myDrumWidth / 2);

		// Position des Freds
		PVector fredPos = ov.get();
		fredPos.mult(-_myDrumWidth / 2);

		for (DrumSingle myDrum : _myDrums) {

			// Verschiebungsvektor vom COM
			PVector translation = new PVector(ov.x, ov.y);
			translation.mult(myDrum.padding * _myDrumSpace);

			// Verschiebungsvektor 2 vom COM
			PVector translation2 = new PVector(ov.x, ov.y);
			translation2.mult((myDrum.padding * _myDrumSpace) - _myDrumWidth / 2);

			// Start und Ende verschieben
			myDrum.start().set(neckPos);
			myDrum.start().add(translation);

			myDrum.end().set(fredPos);
			myDrum.end().add(translation);

			// Mittelvector
			myDrum.center().set(neckPos);
			myDrum.center().add(translation2);
		}

		draw(player);

	}

	private void checkCollision(PVector handAbsolute, PVector hand, DrumSingle myDrum, boolean left) {

		PVector ov = myDrum.ov(true);

		// Dot Product
		float dotProduct = handAbsolute.dot(ov);

		// Entfernung berechnen
		float distance = hand.dist(myDrum.center());
		float maxDistance = _myDrumSpace / 2;

		if (distance <= maxDistance) {
			// Crap
			if (left) {
				if (myDrum.dotProductLeft < 0 && dotProduct > 0) {

					System.out.println("hit up " + myDrum.id);
					// midi.playMidi(myDrum.id, myDrum.id, true);

				} else if (myDrum.dotProductLeft > 0 && dotProduct < 0) {

					// midi.playMidi(myDrum.id, myDrum.id, false);
					// System.out.println("hit down " + myDrum.id);
				}
			} else {
				if (myDrum.dotProductRight < 0 && dotProduct > 0) {

					System.out.println("hit up " + myDrum.id);
					// midi.playMidi(myDrum.id, myDrum.id, true);

				} else if (myDrum.dotProductRight > 0 && dotProduct < 0) {

					// midi.playMidi(myDrum.id, myDrum.id, false);
					// System.out.println("hit down " + myDrum.id);
				}
			}

		}
		// Neues Dot Product speichern
		if (left) {
			myDrum.dotProductLeft = dotProduct;
		} else {
			myDrum.dotProductRight = dotProduct;
		}

	}

	public void checkFredMatch(Player player) {
		// PVector v1 = player.getHandLeftAbsolute().get();
		// PVector v2 = player.getHandRightAbsolute().get();
		// PVector v3 = player.getHandLeftAbsolute().get();
		// PVector v4 = player.getHandRightAbsolute().get();

		PVector v1 = player.getHandLeft().get();
		PVector v2 = player.getHandRight().get();

		PVector v3 = player.getHandLeft().get();
		PVector v4 = player.getHandRight().get();

		v1.normalize();
		v2.normalize();

		for (DrumSingle myDrum : _myDrums) {

			// PVector rv = myDrum.rv(true);
			PVector ov = myDrum.ov(true);

			// Collision detection
			checkCollision(v1, v3, myDrum, true);
			checkCollision(v2, v4, myDrum, false);
		}

	}

	public void draw(Player player) {
		System.out.println("Drums drawn");

		p.noStroke();
		p.fill(255, 0, 255, 125);

		p.pushMatrix();
		p.translate(player.getTorso().x, player.getTorso().y);

		for (DrumSingle myDrum : _myDrums) {

			// Line Check
			// p.line(myDrum.start().x, myDrum.start().y, myDrum.end().x,
			// myDrum.end().y);

			p.rect(myDrum.start().x, myDrum.start().y, _myDrumWidth, _myDrumHeight);
			p.ellipse(myDrum.center().x, myDrum.center().y, size, size);

			// End and Start Vectors
			// p.ellipse(myDrum.start().x, myDrum.start().y, 20, 20);
			// p.ellipse(myDrum.end().x, myDrum.end().y, 20, 20);
		}

		p.fill(255);

		p.popMatrix();

		// Draw Player Hands
		//p.ellipse(player.getHandLeft().x, player.getHandLeft().y, size, size);
		//p.ellipse(player.getHandRight().x, player.getHandRight().y, size, size);

	}

}
