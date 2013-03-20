package midi;

import controlP5.ControlP5;
import processing.core.PApplet;

public class MidiTest extends PApplet {
	
	private Midi midi;
	private ControlP5 cp5;
	
	public int pitch = 100; 
	public int strumDelay = 100;
	public int scale = 1;
	public int strings = 3;
	public boolean toggleValue = false;
	
	public void setup(){
		
		midi = new Midi(this);
		cp5 = new ControlP5(this);
		cp5.addSlider("pitch").setRange(80, 127);
		cp5.addSlider("strumDelay").setRange(0, 400);
		cp5.addSlider("scale").setRange(1, 7);
		cp5.addSlider("strings").setRange(1, 6);
		cp5.addToggle("toggle");
		cp5.addButton("strum");
		
	}
	
	public void draw(){
		
		if(toggleValue){
			midi.sendControllerChange(0, 2, pitch);
		}
		
		
		
	}
	
	public void toggle(boolean flag){
		
		toggleValue = flag;
	}
	
	public void strum(){
		//midi.strumChord(scale, strings, strumDelay, false);
	}
	

}
