package views.tabs;

import controlP5.ControlP5;

public class GuitarTab extends Tab {

	private ControlP5 cp;

	public GuitarTab(ControlP5 cp) {

		super("guitar");

		addController(cp.addButton("butto2n").setBroadcast(false).setPosition(100, 100)
				.setSize(40, 40).setBroadcast(true));
		
		
	}
}
