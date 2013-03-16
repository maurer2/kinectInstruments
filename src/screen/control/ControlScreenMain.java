package screen.control;

import kinect.Kinect;
import processing.core.PApplet;
import main.Main;

public class ControlScreenMain extends PApplet {
	private Main p;
	private Kinect kinect;

	private int widthApplet;
	private int heightApplet;

	public ControlScreenMain(Main p, Kinect kinect, int widthApplet, int heightApplet) {
		this.p = p;
		this.kinect = kinect;

		this.widthApplet = widthApplet;
		this.heightApplet = heightApplet;
	}

	public final void setup() {
		size(widthApplet, heightApplet);
		frameRate = 60;
		noStroke();
		smooth();
	}

	public final void draw() {
		background(0);

		image(kinect.context().depthImage(), 20, 20,320,240);

	}

}
