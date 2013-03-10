package instruments;

import main.Main;
import player.Player;

public class DebugInstrument implements IKinectInstrument {
	private Main p;
	private int width, height, size = 25;

	public DebugInstrument(Main p) {
		this.p = p;
	}

	public void update(Player player) {
		p.ellipse(player.getHandLeft().x, player.getHandLeft().y, size, size);
		p.ellipse(player.getHandRight().x, player.getHandRight().y, size, size);

	}

	private void generateInstrument() {

	}

	public void draw() {

	}

}
