package instruments;

import contrabass.Contrabass;
import guitar.Guitar;
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

	public Instruments(Main p, Kinect kinect) {
		this.p = p;
		this.kinect = kinect;
		this.midi = new MidiMain(p);
	}

	public void setCurrentInstrument(int number) {
		switch (number) {
		case 0:
			currentInstrument = new DebugInstrument(p);
			break;
		case 1:
			currentInstrument = new Drums(p, 4, 50, 80, 50, midi);
			break;
		case 2:
			currentInstrument = new Guitar(p, 1, 20, 300, 150, 5, midi);
			break;
		case 3:
			currentInstrument = new Contrabass(p, 1, 20, 200, 200, 5, midi);
			break;
		case 100:
			currentInstrument = new DebugCharts(p);
			break;
		default:
			break;
		}
	}

	public void update() {

		for (Player player : kinect.getPlayers()) {

			p.pushMatrix();
			currentInstrument.update(player);
			p.popMatrix();
		}
	}
}
