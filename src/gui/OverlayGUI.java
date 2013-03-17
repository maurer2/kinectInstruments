package gui;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import player.Player;
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

		buttons.add(new Button(0, p, bWidth, bHeight, gutter, gutter));
		buttons.add(new Button(1, p, bWidth, bHeight, bWidth + gutter * 2, gutter));
		buttons.add(new Button(2, p, bWidth, bHeight, bWidth * 2 + gutter * 3, gutter));
	}

	public void update() {

		for (Player player : p.getPlayers()) {

			for (Button button : buttons) {

				if (button.isMouseOver(player.getHandLeft().x, player.getHandLeft().y)) {
					countdown++;

					if (countdown >= threshold) {
						System.out.println("Selected " + button.id);
						p.getInstruments().setCurrentInstrument(button.id);
						countdown = 0;
					}

				}

				button.draw();

			}

		}

	}

}
