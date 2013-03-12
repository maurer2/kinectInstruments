package midi.keys;

public class Note {
	private String name;
	private int level;
	private int midiNote;
	
	public Note(String name, int level, int midiNote) {
		super();
		this.name = name;
		this.level = level;
		this.midiNote = midiNote;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel(){
		return level;
	}
	public void setLevel(int level){
		this.level = level;
	}
	public int getMidiNote() {
		return midiNote;
	}
	public void setMidiNote(int midiNote) {
		this.midiNote = midiNote;
	}
	@Override
	public String toString() {
		return "Note [name=" + name + ", level=" + level + ", midiNote=" + midiNote + "]";
	}
	
}
