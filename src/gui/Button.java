package gui;

import main.Main;
import processing.core.PImage;
import processing.core.PVector;

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

	private PImage icon;

	public Button(int id, Main p, int colorNormal, int colorHover, int width, int height, int x,
			int y, String iconPath) {
		this.id = id;
		this.p = p;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.colorNormal = colorNormal;
		this.colorHover = colorHover;
		this.colorCurrent = colorNormal;

		icon = p.loadImage(iconPath);
	}

	public void draw() {

		p.pushStyle();
		p.fill(colorCurrent);
		p.rect(x, y, width, height);
		p.popStyle();

		p.image(icon, x+70, y+10, 60, 60);
	}

	public void active() {
		colorCurrent = colorHover;
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
