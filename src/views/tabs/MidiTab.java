package views.tabs;

import controlP5.ControlP5;

public class MidiTab extends Tab {

	private ControlP5 cp;

	public MidiTab(ControlP5 cp) {

		super("midi");

		addController(cp.addButton("button").setBroadcast(false).setPosition(50, 50)
				.setSize(80, 40).setBroadcast(true));
	}
}
