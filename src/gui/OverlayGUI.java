package gui;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import player.Player;
import controlP5.RadioButton;

public class OverlayGUI {
	private Main p;

	private int width = 196;
	private int height = 80;
	private int gutter = 13;

	private int countdownLeft = 0;
	private int countdownRight = 0;
	private int threshold = 200;

	private List<Button> buttons;

	public OverlayGUI(Main p) {
		this.p = p;
		buttons = new ArrayList<>();

		// Controlbuttons erstellen
		createButtons();
	}

	private void createButtons() {

		int color1 = p.color(0, 174, 219, 150);
		int color1h = p.color(0, 174, 219, 256);

		int color2h = p.color(236, 9, 140, 150);
		int color2 = p.color(236, 9, 140, 256);

		int color3 = p.color(209, 17, 65, 150);
		int color3h = p.color(209, 17, 65, 256);

		buttons.add(new Button(1, p, color1, color1h, width, height, gutter, gutter, "drums.png"));
		buttons.add(new Button(2, p, color2h, color2, width, height, width + gutter * 2, gutter,
				"guitar.png"));
		buttons.add(new Button(3, p, color3, color3h, width, height, width * 2 + gutter * 3,
				gutter, "contrabass.png"));
	}

	private void drawFrame() {

		p.pushStyle();
		p.fill(0);
		p.color(0);
		p.noStroke();
		p.rectMode(Main.CORNERS);

		p.rect(0, 0, 627, 106);
		p.rect(0, 0, 13, 480);
		p.rect(627, 0, 627, 480);
		p.rect(0, 467, 0, 480);

		p.popStyle();
	}

	public boolean update() {
		drawFrame();

		for (Button button : buttons) {

			if (p.getInstruments().getCurrentInstrument() == button.id) {
				button.active();
			}

			button.draw();
		}

		for (Player player : p.getPlayers()) {

			for (Button button : buttons) {

				if (button.isMouseOver(player.getHandLeft())) {
					countdownLeft++;

					if (countdownLeft >= threshold) {
						countdownLeft = 0;
						p.getInstruments().setCurrentInstrument(button.id);

						System.out.println("Selected " + button.id);
					}
					return true;
				}

				if (button.isMouseOver(player.getHandRight())) {
					countdownRight++;

					if (countdownRight >= threshold) {
						countdownRight = 0;
						p.getInstruments().setCurrentInstrument(button.id);

						System.out.println("Selected " + button.id);
					}
					return true;
				}

			}

		}
		return false;
	}

}
