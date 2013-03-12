package midi.keys;

import java.util.ArrayList;

import controlP5.ControlP5;
import processing.core.PApplet;

public class KeysTest extends PApplet {
	
	private ControlP5 cp5;
	
	private Keys ks;
	
	
	public void setup(){
		
		ks = new Keys();
		
		Note[] notes = ks.getNotes();
		System.out.println("start "+ notes.length);
		for(int i = 0; i < notes.length; i++){
			//System.out.println(notes[i].toString());
		}
		System.out.println("CDUR: ");
		Key key = ks.getKey("E", "DUR");
		for(int i = 0; i < key.getNotesFromLevel(4).size(); i++){
			System.out.println(key.getNotesFromLevel(4).get(i).toString());
		}
		
		
	}
	
	public void draw(){
		
	}

}
