package instruments;

import drum.Drums;
import player.Player;
import processing.core.PGraphics;
import kinect.Kinect;
import main.Main;
import midi.MidiMain;

public class Instruments {
	private Main p;
	private Kinect kinect;
	private IKinectInstrument currentInstrument;
	private MidiMain midi;
	private PGraphics kinectStage;

	public Instruments(Main p, Kinect kinect, PGraphics kinectStage) {
		this.p = p;
		this.kinect = kinect;
		this.midi = new MidiMain(p);
		this.kinectStage = kinectStage;
	}

	public void setCurrentInstrument(int number) {
		switch (number) {
		case 0:
			currentInstrument = new DebugInstrument(p);
			break;

		case 1:
			currentInstrument = new Drums(p, 5, 30, 100, 100, midi, kinectStage);
			break;

		default:
			break;
		}
	}

	public void update() {

		for (Player player : kinect.getPlayers()) {
			p.pushMatrix();
			//p.translate(player.getTorso().x, player.getTorso().y);
			currentInstrument.update(player);

			p.popMatrix();

			// Hands
			// p.ellipse(player.getHandLeft().x, player.getHandLeft().y, 50,
			// 50);
			// p.ellipse(player.getHandRight().x, player.getHandRight().y, 50,
			// 50);
		}

	}
}
