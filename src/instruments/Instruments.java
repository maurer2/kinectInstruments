package instruments;

import drum.Drums;
import player.Player;
import kinect.Kinect;
import main.Main;

public class Instruments {
	private Main p;
	private Kinect kinect;
	private IKinectInstrument currentInstrument;

	public Instruments(Main p, Kinect kinect) {
		this.p = p;
		this.kinect = kinect;
	}

	public void setCurrentInstrument(int number) {
		switch (number) {
		case 0:
			currentInstrument = new DebugInstrument(p);
			break;

		case 1:
			currentInstrument = new Drums(p, 6, 20, 50, 50);
			break;

		default:
			break;
		}
	}

	public void update() {
		for (Player player : kinect.getPlayers()) {
			p.translate(player.getCenterOfMass().x, player.getCenterOfMass().y);
			currentInstrument.update(player);
		}
	}
}
