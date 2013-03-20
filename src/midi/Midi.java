package midi;

import java.util.ArrayList;

import midi.keys.Key;
import midi.keys.Keys;
import midi.keys.Note;
import processing.core.PApplet;
import themidibus.MidiBus;

public class Midi {

	public MidiBus bus;
	private int channel;
	private int velocity = 80;
	private PApplet p;
	private Keys keys;
	private Key key;

	public Midi(PApplet p) {
		channel = 0;
		this.p = p;
		MidiBus.list();
		bus = new MidiBus(p, 0, 3);
		keys = new Keys();
		key = keys.getKey("A", "DUR");
	}

	public void setKey(String noteName, String key) {
		this.key = keys.getKey(noteName, key);
	}

	private float rand() {
		return this.p.random(0.1f, 1.0f);

	}

	public void strumChord(int scale, int numStrings, int delay, boolean up,float velocity, int channel) {
		if (scale < 1 || scale > 7)
			return;
		if (numStrings > 6)
			return;
		int numStrngsTmp = numStrings;

		ArrayList<Note> notes = key.getNotesFromLevel(3);
		int index = scale - 1;
		int inc = 2;
		if (!up) {
			index = scale - 1 + ((numStrings - 1) * 2);
			inc = -2;
		}

		for (int i = index; i < notes.size(); i = i + inc) {
			if (numStrings <= 0)
				break;
			noteOn(channel, notes.get(i).getMidiNote(), (int)velocity);
			numStrings--;
			// this.p.delay((int)(20 * rand()));
			if (numStrings == 0)
				break;
		}

		this.p.delay((int) (delay * rand()));

		numStrings = numStrngsTmp;
		for (int i = index; i < notes.size(); i = i + inc) {
			if (numStrings <= 0)
				break;
			// noteOff(channel, notes.get(i).getMidiNote(), velocity);
			numStrings--;
			this.p.delay((int) (20 * rand()));
			if (numStrings == 0)
				break;
		}

	}

	public void strumChordString(int scale, int numStrings, int delay, boolean up, int string) {
		
		if (numStrings > 6)
			return;
		int numStrngsTmp = numStrings;

		ArrayList<Note> notes = key.getNotesFromLevel(3);
		int index = scale - 1;
		int inc = 2;
		if (!up) {
			index = scale - 1 + ((numStrings - 1) * 2);
			inc = -2;
			string = numStrings - string;
		}
		int h = 0;
		for (int i = index; i < notes.size(); i = i + inc) {
			h++;
			if (numStrings <= 0)
				break;
			if (h == string) {
				noteOn(channel, notes.get(i).getMidiNote(), velocity);
				// this.p.delay((int)(20 * rand()));
			}
			numStrings--;
			if (numStrings == 0)
				break;
		}

		this.p.delay((int) (delay * rand()));

		numStrings = numStrngsTmp;
		h = 0;
		for (int i = index; i < notes.size(); i = i + inc) {
			h++;
			if (numStrings <= 0)
				break;
			if (h == string) {
				noteOff(channel, notes.get(i).getMidiNote(), velocity);
				// this.p.delay((int)(20 * rand()));
			}
			numStrings--;
			if (numStrings == 0)
				break;
		}

	}

	public void noteOn(int channel, int pitch, int velocity) {
		int vel = this.velocity;
		if (velocity != -1)
			vel = velocity;
		bus.sendNoteOn(channel, pitch, vel);
	}

	public void noteOff(int channel, int pitch, int velocity) {
		int vel = this.velocity;
		if (velocity != -1)
			vel = velocity;
		bus.sendNoteOff(channel, pitch, vel);
	}

	public void sendControllerChange(int channel, int number, int value) {
		bus.sendControllerChange(channel, number, value);

	}

}
