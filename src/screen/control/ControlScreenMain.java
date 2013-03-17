package screen.control;

import controlP5.ControlEvent;
import controlP5.ControlP5;
import kinect.Kinect;
import processing.core.PApplet;
import processing.core.PFont;
import views.tabs.GuitarTab;
import views.tabs.MidiTab;
import views.tabs.TabNavigation;
import main.Main;

public class ControlScreenMain extends PApplet {
	private Main p;
	private Kinect kinect;

	private int widthApplet;
	private int heightApplet;

	private ControlP5 cp;
	private TabNavigation nav;

	private ControlTab ct;
	private PFont font;

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

		stroke(255, 0, 255);
		noFill();
		strokeWeight(4);

		cp = new ControlP5(this);
		ct = new ControlTab(this, cp, kinect);
		font = createFont("Verdana", 16, true);
		cp.setFont(font);

		// nav = new TabNavigation(cp);
		// nav.addTab(new MidiTab(cp));
		// nav.addTab(new GuitarTab(cp));
	}

	public final void draw() {
		// background(255);

		// ct.createTabs();
		// text(c, x, y)

		ct.update();
		// image(kinect.context().depthImage(), 20, 20, 320, 240);

	}

	public Kinect getKinect() {
		return kinect;
	}

	// Tab Events
	public void controlEvent(ControlEvent theControlEvent) {
		if (theControlEvent.isTab()) {
			println("got an event from tab : " + theControlEvent.getTab().getName() + " with id "
					+ theControlEvent.getTab().getId());
			
			// Play instrument
			p.getInstruments().setCurrentInstrument(1);
		}
	}

}
