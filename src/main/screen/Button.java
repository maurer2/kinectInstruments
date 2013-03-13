package main.screen;

import java.awt.Color;

import main.Main;

import processing.core.PApplet;
import processing.core.PMatrix3D;

public class Button {
	private Main p;
	private int size = 140;
	private int x;
	private int y;
	private int[] colorNormal;
	private int[] colorHover;
	private int[] colorCurrent;
	
	public Button(Main p, int x, int y){
		this.p=p;
		this.x = x;
		this.y = y;
		colorCurrent = new int[] {255, 0, 255};
	}
	
	public void draw(){
		p.pushStyle();
		p.color(p.color(colorCurrent[0],colorCurrent[1],colorCurrent[2]));
		p.rect(x,y, size, size);
		
		p.popStyle();
	}
}
