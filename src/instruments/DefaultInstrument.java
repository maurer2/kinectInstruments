package instruments;

import main.Main;
import player.LimbVector;
import player.Player;
import processing.core.PVector;

public class DefaultInstrument implements IKinectInstrument {
	private Main p;
	private int size = 20;

	public DefaultInstrument(Main p) {
		this.p = p;
	}

	public void update(Player player) {
		size = 20;
	}

	private void drawJoints(LimbVector v) {
		p.pushStyle();
		p.fill(255, 0, 255, p.map(v.confidence, 0, 1, 0, 255));
		p.ellipse(v.x, v.y, size, size);
		p.popStyle();
	}

	private void drawJoints(PVector v) {
		p.pushStyle();
		p.fill(255, 0, 255);
		p.ellipse(v.x, v.y, size, size);
		p.popStyle();
	}
}
