package instruments;

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

		default:
			break;
		}
	}

	public void update() {
		for (Player player : kinect.getPlayers()) {
			currentInstrument.update(player);
		}
	}
}
