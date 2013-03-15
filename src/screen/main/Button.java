package screen.main;

import java.awt.Color;

import main.Main;

import processing.core.PApplet;
import processing.core.PMatrix3D;

public class Button {
	private Main p;
	private int size = 50;
	private int height = 50;
	private int x;
	private int y;
	private int[] colorNormal;
	private int[] colorHover;
	private int[] colorCurrent;
	
	public Button(Main p, int size, int height, int x, int y){
		this.p=p;
		this.x = x;
		this.y = y;
		this.size = size;
		this.height = height;
		colorCurrent = new int[] {255, 0, 255};
	}
	
	public void draw(){
		p.pushStyle();
		p.color(p.color(colorCurrent[0],colorCurrent[1],colorCurrent[2]));
		p.rect(x,y, size, height);
		
		p.popStyle();
	}
}
