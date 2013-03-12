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

	List<DrumSingle> drumsList = new ArrayList<>();

	private float numberDrums;
	private float marginDrums; 
	private float widthDrums; 
	private float heightDrums; 

	public boolean debug = true;

	// private Midi midi;

	public Drums(Main p, float numberDrums, float marginDrums, float widthDrums, float heightDrums) {
		this.p = p;

		this.numberDrums = numberDrums;
		this.marginDrums = marginDrums + widthDrums;
		this.widthDrums = widthDrums;
		this.heightDrums = heightDrums;

		// Drums erstellen
		generateDrums(numberDrums);

		System.out.println("Drums loaded");
	}

	private void generateDrums(float numberDrums) {
		drumsList.clear();
		if (numberDrums < 1)
			return;
		float padding = -(numberDrums - 1) / 2;
		for (int i = 0; i < numberDrums; i++) {
			drumsList.add(new DrumSingle(padding, i));
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
		neckPos.mult(widthDrums / 2);

		// Position des Freds
		PVector fredPos = ov.get();
		fredPos.mult(-widthDrums / 2);

		for (DrumSingle myDrum : drumsList) {

			// Verschiebungsvektor vom COM
			PVector translation = new PVector(ov.x, ov.y);
			translation.mult(myDrum.padding * marginDrums);

			// Verschiebungsvektor 2 vom COM
			PVector translation2 = new PVector(ov.x, ov.y);
			translation2.mult((myDrum.padding * marginDrums) - widthDrums / 2);

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
		float maxDistance = marginDrums / 2;

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

		for (DrumSingle myDrum : drumsList) {

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

		for (DrumSingle myDrum : drumsList) {

			// Line Check
			// p.line(myDrum.start().x, myDrum.start().y, myDrum.end().x,
			// myDrum.end().y);

			p.rect(myDrum.start().x, myDrum.start().y, widthDrums, heightDrums);
			p.ellipse(myDrum.center().x, myDrum.center().y, size, size);

			// End and Start Vectors
			// p.ellipse(myDrum.start().x, myDrum.start().y, 20, 20);
			// p.ellipse(myDrum.end().x, myDrum.end().y, 20, 20);
		}

		p.fill(255);

		p.popMatrix();

		// Draw Player Hands
		 p.ellipse(player.getHandLeft().x, player.getHandLeft().y, size,size);
		// p.ellipse(player.getHandRight().x, player.getHandRight().y, size,
		// size);

	}

}
