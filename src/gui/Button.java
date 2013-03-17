package gui;

import processing.core.PApplet;
import processing.core.PVector;
import main.Main;

public class Button {
	private Main p;
	public int id;
	private int width;
	private int height;
	private int x;
	private int y;
	private int colorNormal;
	private int colorHover;
	private int colorCurrent;

	public Button(int id, Main p, int colorNormal, int width, int height, int x, int y) {
		this.id = id;
		this.p = p;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.colorNormal = colorNormal;
		this.colorHover = p.color(colorNormal, 200);
		this.colorCurrent = colorNormal;
	}

	public void draw() {
		p.pushStyle();
		p.fill(colorCurrent);
		p.rect(x, y, width, height);

		p.popStyle();
	}

	private void hover(boolean hover) {
		if (hover) {
			colorCurrent = colorHover;
		} else {
			colorCurrent = colorNormal;
		}
	}

	public boolean isMouseOver(PVector v) {
		if (v.x >= x && v.x <= x + width && v.y >= y && v.y <= y + height) {
			hover(true);
			return true;

		} else {
			hover(false);
			return false;
		}

	}
}
