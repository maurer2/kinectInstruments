package screen.control;

import java.awt.Color;
import java.awt.Frame;

import kinect.Kinect;
import main.IUpdate;
import main.Main;

public class ControlScreen implements IUpdate {
	private Main p;
	private Kinect kinect;
	private ControlScreenMain csm;

	public ControlScreen(Main p, Kinect kinect) {
		this.p = p;
		this.kinect = kinect;

		// Create ControlScreen
		createControlScreen("K.U.N.T. - Kinect Control Unit ", 800, 800);
	}

	private void createControlScreen(String name, int widthApplet, int heightApplet) {
		int border = 20;
		Frame frame = new Frame(name);

		csm = new ControlScreenMain(p, kinect, widthApplet, heightApplet);

		frame.add(csm);
		csm.init();

		frame.setTitle(name);
		frame.setSize(widthApplet, heightApplet);
		frame.setLocation(p.width + border, 0);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void update() {
		// TODO Auto-generated method stub

	}

}
