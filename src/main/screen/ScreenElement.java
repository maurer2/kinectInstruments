package main.screen;

import main.Main;
import processing.core.PGraphics;
import processing.core.PImage;

public class ScreenElement {
	private PImage image;
	private Main p;

	public ScreenElement(Main p, PImage image) {
		this.p = p;
		this.image = image;
	}

	public void setImage(PGraphics image) {
		this.image = image;
	}

	public void draw(int x, int y) {
		// p.image(image, x, y);

	}
}
