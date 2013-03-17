package gui;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import player.Player;
import controlP5.ControlP5;
import controlP5.RadioButton;
import controlP5.Toggle;

public class OverlayGUI {
	private Main p;
	private ControlP5 cp5;
	private RadioButton controlBar;

	private int buttonWidth = 210;
	private int buttonHeight = 60;
	private int margin = 10;

	private int countdown = 0;
	private int threshold = 100;

	private List<Button> buttons;

	public OverlayGUI(Main p) {
		this.p = p;
		cp5 = new ControlP5(p);

		// Controlbar erstellen
		// createBar();

		buttons = new ArrayList<>();

		// button1 = new Button(p, 147, 80, 0, 0);
		createButtons();
	}

	private void createBar() {
		controlBar = cp5.addRadioButton("radiobuttons").setPosition(0, 0)
				.setSize(buttonWidth, buttonHeight).setColorValue(0)
				.setColorForeground(p.color(255)).setColorActive(p.color(255, 0, 255))
				.setItemsPerRow(3).setSpacingColumn(10).setHeight(buttonHeight)
				.setBackgroundColor(0).setBackgroundHeight(buttonHeight + margin)
				.addItem("Guitar", 0).addItem("Drums", 1).addItem("Contrabass", 2).hideLabels();

	}

	private void createButtons() {
		int buttonWidth = 196;
		int buttonHeight = 70;
		int gutter = 13;

		buttons.add(new Button(0, p, buttonWidth, buttonHeight, gutter, gutter));
		buttons.add(new Button(1, p, buttonWidth, buttonHeight, buttonWidth + gutter * 2, gutter));
		buttons.add(new Button(2, p, buttonWidth, buttonHeight, buttonWidth * 2 + gutter * 3,
				gutter));
	}

	public void update() {

		for (Toggle button : controlBar.getItems()) {

			if (button.isMouseOver()) {
				countdown++;
				// System.out.println("Mouse inside");

				if (countdown >= threshold) {
					// button.toggle();
					button.setValue(true);
					countdown = 0;
				}
			}
		}

	}

	public void update2() {

		for (Player player : p.getPlayers()) {

			for (Button button : buttons) {

				if (button.isMouseOver(player.getHandLeft().x, player.getHandLeft().y)) {
					countdown++;
					// System.out.println("Inside " + button.id + "- " +
					// player.getHandLeft().x);

					if (countdown >= threshold) {
						System.out.println("Selected " + button.id);
						countdown = 0;
					}

				}

				button.draw();

			}

		}

	}

	public int getHeight() {
		return controlBar.getHeight();
	}

	public int getWidth() {
		return controlBar.getWidth();
	}
}
