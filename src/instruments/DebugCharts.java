package instruments;

import java.util.HashMap;
import java.util.Map.Entry;

import main.Main;
import player.Player;
import processing.core.PVector;
import controlP5.Chart;
import controlP5.ControlP5;

public class DebugCharts implements IKinectInstrument {
	private Main p;
	private int size = 20;

	private ControlP5 cp5;
	private HashMap<String, Chart> charts = new HashMap<>();

	public DebugCharts(Main p) {
		this.p = p;

		cp5 = new ControlP5(p);

		Chart angleChart = cp5.addChart("Angle").setPosition(50, 70).setSize(300, 100)
				.setRange(0, 200).setView(Chart.AREA).addDataSet("angle");

		Chart velocityLeftChart = cp5.addChart("Velocity Left").setPosition(50, 190)
				.setSize(300, 100).setColorBackground(p.color(255, 0, 255)).setRange(-90, +90)
				.setView(Chart.LINE).addDataSet("velocity Left");

		Chart accelerationLeftChart = cp5.addChart("Acceleration Left").setPosition(50, 310)
				.setSize(300, 100).setColorBackground(p.color(255, 0, 255)).setRange(-90, +90)
				.setView(Chart.LINE).addDataSet("acceleration Left");

		charts.put("angle", angleChart);
		charts.put("velocity Left", velocityLeftChart);
		charts.put("acceleration Left", accelerationLeftChart);

		// Populate
		setStuff(60);
	}

	public void update(Player player) {
		p.stroke(255, 0, 255);
		p.noFill();
		p.strokeWeight(4);

		PVector elbowHandLeft = player.getElbowHandLeft();
		PVector elbowShoulderLeft = player.getElbowShoulderLeft();

		PVector elbowHandRight = player.getElbowHandRight();
		PVector elbowShoulderRight = player.getElbowShoulderRight();

		// Translate zum COM
		// p.translate(player.getTorso().x, player.getTorso().y);

		p.ellipse(player.getTorso().x, player.getTorso().y, size, size);

		// Draw
		p.line(player.getElbowLeft().x, player.getElbowLeft().y, player.getShoulderLeft().x,
				player.getShoulderLeft().y);
		p.line(player.getElbowLeft().x, player.getElbowLeft().y, player.getHandLeft().x,
				player.getHandLeft().y);

		p.line(player.getElbowRight().x, player.getElbowRight().y, player.getShoulderRight().x,
				player.getShoulderRight().y);
		p.line(player.getElbowRight().x, player.getElbowRight().y, player.getHandRight().x,
				player.getHandRight().y);

		// Angle
		elbowHandLeft.normalize();
		elbowShoulderLeft.normalize();

		// Charts
		float angle = player.getAngleLeft();
		float velocity = player.getVelocityLeft();
		float acceleration = player.getAccelerationLeft();

		charts.get("angle").push("angle", angle);
		charts.get("velocity Left").push("velocity Left", velocity);
		charts.get("acceleration Left").push("acceleration Left", acceleration);

	}

	private void setStuff(int frameRateCharts) {
		for (Entry<String, Chart> e : charts.entrySet()) {
			e.getValue().getColor().setBackground(p.color(255, 100));
			e.getValue().setStrokeWeight(2f);
			e.getValue().setColors(e.getKey(), p.color(255), p.color(0, 255, 0));
			e.getValue().setData(e.getKey(), new float[frameRateCharts]);
		}

	}

}
