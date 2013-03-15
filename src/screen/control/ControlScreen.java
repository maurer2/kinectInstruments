package screen.control;

import kinect.Kinect;
import main.IUpdate;
import main.Main;

public class ControlScreen implements IUpdate {
	private Main p;
	private Kinect kinect;

	public ControlScreen(Main p, Kinect kinect) {
		this.p = p;
		this.kinect = kinect;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
