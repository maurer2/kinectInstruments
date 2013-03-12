package midi.keys;

import java.util.ArrayList;
import java.util.HashMap;

public class Keys {

	private final String[] noteNames = { "C", "C#/Db", "D", "D#/Eb", "E", "F",
			"F#/Gb", "G", "G#/Ab", "A", "A#/Hb", "H" };
	private final int[] dur = { 2, 2, 1, 2, 2, 2, 1 };
	private final int[] mol = { 2, 1, 2, 2, 1, 2, 1 };
	private HashMap<String, int[]> keyPatterns = new HashMap<>();
	private Note[] notes;

	public Keys() {
		keyPatterns.put("DUR", dur);
		keyPatterns.put("MOL", mol);
		generateNotes();
	}

	public Key getKey(String noteName, String keyName) {
		int[] keyAr = keyPatterns.get(keyName);
		int index = indexOfNote(noteName);
		Key key = new Key(noteName, keyName);
		if (index == -1) {
			return null;
		}
		int j = 0;
		for (int i = index; i < notes.length; i++) {
			key.addNote(notes[i]);
			if (keyAr[j] == 2) {
				i++;
			}
			j++;
			if(j == keyAr.length) j = 0;
		}

		return key;

	}
	
	public Note[] getNotes(){
		
		return notes;
	}

	private int indexOfNote(String noteName) {
		for (int i = 0; i < noteNames.length; i++) {
			if (noteNames[i] == noteName) {
				return i;
			}
		}
		return -1;
	}

	private void generateNotes() {
		
		int midiStartVal = 24;
		int level = 1;
		notes = new Note[noteNames.length*8];
		int j = 0;
		for (int i = 0; i < noteNames.length; i++) {
			notes[j] = new Note(noteNames[i], level, midiStartVal++);
			if(i == noteNames.length-1){
				level++;
				i = -1;
			} 
			if(level > 8) break;
			j++;
		}

	}
}
