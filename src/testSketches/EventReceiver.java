package testSketches;

import processing.core.PApplet;
import controlP5.*;

public class EventReceiver extends PApplet {
	ControlP5 controlP5;
	MyControlListener myListener;

	public void setup() {
		size(400, 400);
		frameRate(30);
		controlP5 = new ControlP5(this);
		controlP5.addSlider("mySlider", 100, 200, 140, 200, 200, 100, 10);

		myListener = new MyControlListener();
		controlP5.controller("mySlider").addListener(myListener);
	}

	public void draw() {
		background(myListener.col);
	}

	class MyControlListener implements ControlListener {
		int col;

		public void controlEvent(ControlEvent theEvent) {
			println("i got an event from mySlider, " + "changing background color to "
					+ theEvent.controller().value());
			col = (int) theEvent.controller().value();
		}
	}
}