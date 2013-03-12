package midi.keys;

import java.util.ArrayList;

public class Key {
	
	private ArrayList<Note> notes = new ArrayList<Note>();
	

	private String note;
	private String key;
	public Key(String noteName, String keyName){
		this.note = noteName;
		this.key = keyName;
		
		
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public void addNote(Note note){
		notes.add(note);
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
	
	public ArrayList<Note> getNotesByLevel(int level){
		ArrayList<Note> notesByLevel = new ArrayList<Note>();
		for(int i = 0; i < notes.size(); i++){
			
			if(notes.get(i).getLevel() == level){
				notesByLevel.add(notes.get(i));
			}
		}
		return notesByLevel;
	}
	public ArrayList<Note> getNotesFromLevel(int level){
		ArrayList<Note> notesByLevel = new ArrayList<Note>();
		level -= 1;
		for(int i = 7*level; i < notes.size(); i++){
			
			notesByLevel.add(notes.get(i));
		}
		return notesByLevel;
	}
}
