package gui;

import java.awt.Color;

import main.Main;

import processing.core.PApplet;
import processing.core.PMatrix3D;

public class Button {
	private Main p;
	public int id;
	private int width = 140;
	private int height = 50;
	private int x;
	private int y;
	private int colorNormal;
	private int colorHover;
	private int colorCurrent;

	public Button(int id, Main p, int width, int height, int x, int y) {
		this.id = id;
		this.p = p;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		colorNormal = p.color(255);
		colorCurrent = colorNormal;
		colorHover = p.color(255, 0, 255);
	}

	public void draw() {
		p.pushStyle();
		p.color(colorCurrent);
		p.rect(x, y, width, height);

		p.popStyle();
	}

	public boolean isMouseOver(int posX, int posY) {

		if (posX >= x && posX <= x + width && posY >= y && posY <= y + height) {
			return true;

		} else {
			return false;
		}

	}

	private void hover(boolean hover) {
		if (hover) {
			colorCurrent = colorHover;
		} else {
			colorCurrent = colorNormal;
		}
	}

	public boolean isMouseOver(float posX, float posY) {
		// posX = posX / 1.4f;
		// posY = posY / 1.4f;

		if (posX >= x && posX <= x + width && posY >= y && posY <= y + height) {
			hover(true);
			return true;

		} else {
			hover(false);
			return false;
		}

	}
}
