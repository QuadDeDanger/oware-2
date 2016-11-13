package view;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * This class is used in the design of a House on the Board. Comments by Aqib
 * Rashid
 * 
 * @author Ajeya Jog
 * @author Federico Midolo
 *
 */

public class HouseView extends BorderPane {
	private int count;
	private int x;
	private int y;
	private Group seeds;
	private StackPane stackHouse;
	private StackPane stackLabel;
	private Label label;

	/**
	 * Creates a new HouseView object. It stores the relevant information like x
	 * position and y position as well as setting up the Circles (to be used as
	 * seeds).
	 * 
	 * @param x
	 *            the x position of the house
	 * @param y
	 *            the y position of the house
	 * @param r
	 *            the radius of the circle (seed)
	 */
	public HouseView(int x, int y, int r) {
		super();
		this.x = x;
		this.y = y;
		this.count = 0;

		stackHouse = new StackPane(); // houses the seeds
		stackLabel = new StackPane(); // houses the counter label
		seeds = new Group();
		label = new Label("0");

		// create a new circle at pos x, y of radius r
		Circle back = new Circle(x, y, r, Color.web("#eb505d"));
		Rectangle rect = new Rectangle(14, 30, Color.web("#1b1b1c"));

		label.setTextFill(Color.web("#ffffff"));
		label.setPadding(new Insets(0, 0, 10, 0));

		// Position in center
		stackHouse.setAlignment(Pos.CENTER);
		stackLabel.setAlignment(Pos.CENTER);

		// Add all child objects to the parent pane
		stackHouse.getChildren().add(back);
		stackHouse.getChildren().add(seeds);
		stackLabel.getChildren().add(rect);
		stackLabel.getChildren().add(label);

		setCenter(stackHouse);
		setTop(stackLabel);
		setAlignment(stackLabel, Pos.CENTER);
	}

	/*
	 * This method updates the counter i.e. number of seeds in the house shown
	 * above/below
	 */
	private void updateLabel() {
		label.setText(String.valueOf(count));
	}

	/*
	 * Fades out a seed when it is removed from the hose
	 */
	private void seedFadeOut() {
		FadeTransition ft = new FadeTransition(Duration.millis(500), seeds);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();
		ft.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				seeds.getChildren().clear();
			}
		});
	}

	/*
	 * Fades in a seed when it is removed from the hose
	 */
	private void seedFadeIn(SeedView s) {
		// transition speed
		FadeTransition ft = new FadeTransition(Duration.millis(500), s);
		ft.setFromValue(0);
		ft.setToValue(1);
		FillTransition fillT = new FillTransition();

		// use the seedview object passed in to create a new shape
		fillT.setShape(s);
		fillT.setDuration(Duration.millis(300));
		fillT.setToValue(Color.web("#2a282d"));
		FillTransition reverseFillT = new FillTransition();
		reverseFillT.setShape(s);
		reverseFillT.setDuration(Duration.millis(300));
		reverseFillT.setToValue(Color.WHITE);
		SequentialTransition sq = new SequentialTransition();
		sq.getChildren().addAll(ft, fillT, reverseFillT);
		sq.play();
	}

	/**
	 * This method adds seeds to the houseview itself by randomising their
	 * positions and updates the total in the house
	 * 
	 * @param s
	 *            the number of seeds to add
	 */
	public void addSeeds(int s) {
		for (int i = 0; i < s; i++) {
			SeedView seed = new SeedView((int) (x + (Math.random() * 30)), (int) (y + (Math.random() * 30)), 10);
			seed.setOpacity(0);
			seeds.getChildren().add(seed);
			count++;
			updateLabel();
			seedFadeIn(seed);
		}
	}

	/**
	 * This method is similar to the above but only adds a single seed to the
	 * House and updates the counter
	 */
	public void addOneSeed() {
		SeedView seed = new SeedView((int) (x + (Math.random() * 30)), (int) (y + (Math.random() * 30)), 10);
		seed.setOpacity(0);
		seeds.getChildren().add(seed);
		count++;
		updateLabel();
		seedFadeIn(seed);
	}

	/**
	 * Removes all seeds from the house and updates the counter
	 */
	public void removeSeeds() {
		count = 0;
		updateLabel();
		seedFadeOut();
	}

	/**
	 * Return x position of the house
	 * 
	 * @return x position of hose
	 */
	public int getX() {
		return x;
	}

	/**
	 * Return y position of the house
	 * 
	 * @return y position of hose
	 */
	public int getY() {
		return y;
	}

	/**
	 * Return the number of seeds in the house
	 * 
	 * @return number of seeds in the house
	 */
	public int getSize() {
		return count;
	}

	/**
	 * Sets the counter label to be at the bottom rather than the top
	 */
	public void setBottom() {
		setTop(null);
		setBottom(stackLabel);
		label.setPadding(new Insets(10, 0, 0, 0));
	}

	/**
	 * Clears the house and updates the counter
	 */
	public void clear() {
		seeds.getChildren().clear();
		count = 0;
		updateLabel();
	}
}
