package midi;

import main.Main;
import guitar.Guitar;
import instruments.DebugCharts;
import instruments.DefaultInstrument;
import contrabass.Contrabass;
import drum.Drums;
import processing.core.PApplet;

public class MidiMain {
	midi.Midi midi;
	PApplet p;

	public MidiMain(PApplet p) {
		midi = new midi.Midi(p);
		this.p = p;
	}

	public void playMidi(int myString, int myHead, boolean upAndDown) {
		// midi.strumChord(myHead + 1, 3, 1, upAndDown);
	}

	public void playMidi(int myString, int myHead, boolean upAndDown, float velocity, int channel) {		
		velocity = PApplet.map(velocity, -100, 100, 50, 100);
		System.err.println(velocity);
		midi.strumChord(myHead + 1, 3, 1, upAndDown, velocity, channel);
	}
	
	public void playMidiContrabass(int myString, int myHead, boolean upAndDown, float velocity, int channel) {		
		velocity = PApplet.map(velocity, 1, 2, 50, 100);
		System.err.println(velocity);
		midi.strumChord(myHead + 1, 3, 1, upAndDown, velocity, channel);
	}

	public void playMidiDrum(float velocity, int channel, int drumID) {		
		velocity = PApplet.map(velocity, -100, 100, 20, 100);
		System.err.println(velocity);
		int note;
		switch (drumID) {
		case 0:
			note = 60;
			break;
		case 1:
			note = 62;
			break;
		case 2:
			note = 64;
			break;
		case 3:
			note = 65;
			break;
		case 4:
			note = 67;
			break;
		default:
			return;
		}
		midi.noteOn(channel, note, Math.round(velocity));
		//p.delay(100);
		//midi.noteOff(channel, note, Math.round(velocity));

	}

}