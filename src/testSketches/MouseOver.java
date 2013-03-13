package testSketches;

import processing.core.PApplet;
import controlP5.*;

public class MouseOver extends PApplet {

	ControlP5 cp5;
	int hover = color(0, 230, 150);

	public int slider1 = 64;
	public int slider2 = 128;

	public void setup() {
		size(700, 400);
		smooth();
		cp5 = new ControlP5(this);

		cp5.addSlider("slider1", 0, 255, 20, 100, 128, 20);
		cp5.addSlider("slider2", 0, 255, 20, 150, 128, 20);

		ListBox l = cp5.addListBox("myList", 250, 260, 100, 80);
		for (int i = 0; i < 80; i++) {
			l.addItem("item " + i, i);
		}

		cp5.addButton("b1", 0, 20, 350, 80, 12);
		cp5.addButton("b2", 0, 101, 350, 80, 12);

	}

	

	public void draw() {
		background(0);
		// check if the mouse is inside of any of the controllers
		// displayed in the main window
		if (cp5.isMouseOver()) {
			fill(hover);
		} else {
			fill(128);
		}

		ellipse(45, 50, 50, 50);

		// check if the mouse is hovering controller slider1 and set the color
		// accordingly
		fill(cp5.isMouseOver(cp5.getController("slider1")) ? hover : color(slider1));
		rect(250, 100, 100, 20);

		fill(cp5.isMouseOver(cp5.getController("slider2")) ? hover : color(slider2));
		rect(250, 150, 100, 20);

		fill(cp5.isMouseOver(cp5.getController("b1")) ? hover : color(128));
		ellipse(30, 330, 20, 20);

		fill(cp5.isMouseOver(cp5.getController("b2")) ? hover : color(128));
		ellipse(110, 330, 20, 20);

		fill(cp5.isMouseOver(cp5.getGroup("myList")) ? hover : color(128));
		ellipse(260, 230, 20, 20);

	}

	public void mousePressed() {
		// print the current mouseoverlist on mouse pressed
		print("The Current mouseoverlist:\t");
		println(cp5.getWindow().getMouseOverList());
	}

}
