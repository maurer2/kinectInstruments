package instruments;

import main.Main;
import player.LimbVector;
import player.Player;

public class DebugInstrument implements IKinectInstrument {
	private Main p;
	private int size = 20;

	public DebugInstrument(Main p) {
		this.p = p;
	}

	public void update(Player player) {
		p.pushStyle();
		p.noStroke();

		drawJoints(player.getHandLeft());
		drawJoints(player.getHandRight());

		drawJoints(player.getHead());
		drawJoints(player.getNeck());

		drawJoints(player.getShoulderLeft());
		drawJoints(player.getShoulderRight());

		drawJoints(player.getCenterOfMass());
		drawJoints(player.getTorso());

		drawJoints(player.getElbowLeft());
		drawJoints(player.getElbowRight());

		drawJoints(player.getHipLeft());
		drawJoints(player.getHipRight());

		p.popStyle();
	}

	private void generateInstrument() {

	}

	private void drawJoints(LimbVector v) {
		p.pushStyle();
		p.fill(255, 0, 255, p.map(v.confidence, 0, 1, 0, 255));
		p.ellipse(v.x, v.y, size, size);
		p.popStyle();
	}

	public void draw() {

	}

}
