package gui;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import player.Player;
import processing.core.PApplet;
import controlP5.RadioButton;

public class OverlayGUI {
	private Main p;
	private RadioButton controlBar;

	private int bWidth = 196;
	private int bHeight = 70;
	private int gutter = 13;

	private int countdown = 0;
	private int threshold = 100;

	private List<Button> buttons;

	public OverlayGUI(Main p) {
		this.p = p;
		buttons = new ArrayList<>();

		// Controlbuttons erstellen
		createButtons();
	}

	private void createButtons() {

		int color1 = p.color(0, 174, 219);
		int color2 = p.color(236, 9, 140);
		int color3 = p.color(209, 17, 65);

		buttons.add(new Button(0, p, color1, bWidth, bHeight, gutter, gutter));
		buttons.add(new Button(1, p, color2, bWidth, bHeight, bWidth + gutter * 2, gutter));
		buttons.add(new Button(2, p, color3, bWidth, bHeight, bWidth * 2 + gutter * 3, gutter));
	}

	public boolean update() {

		for (Player player : p.getPlayers()) {

			for (Button button : buttons) {
				
				button.draw();
				
				if (button.isMouseOver(player.getHandLeft())) {
					countdown++;

					if (countdown >= threshold) {
						System.out.println("Selected " + button.id);
						p.getInstruments().setCurrentInstrument(button.id);
						countdown = 0;
					}
				return true;

				}

			}

		}
		return false;
	}

}
