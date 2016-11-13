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

public class HouseView extends BorderPane {
	private int count;
	private int x;
	private int y;
    private Group seeds;
    private StackPane stackHouse;
    private StackPane stackLabel;
    private Label label;

	public HouseView(int x, int y, int r) {
		super();
		this.x = x;
		this.y = y;
		this.count = 0;

        stackHouse = new StackPane();
        stackLabel = new StackPane();
        seeds = new Group();
        label = new Label("0");
        Circle back = new Circle(x,y,r, Color.web("#eb505d"));
        Rectangle rect = new Rectangle(14,30,Color.web("#1b1b1c"));

        label.setTextFill(Color.web("#ffffff"));
        label.setPadding(new Insets(0,0,10,0));

        stackHouse.setAlignment(Pos.CENTER);
        stackLabel.setAlignment(Pos.CENTER);

        stackHouse.getChildren().add(back);
        stackHouse.getChildren().add(seeds);
        stackLabel.getChildren().add(rect);
        stackLabel.getChildren().add(label);

        setCenter(stackHouse);
        setTop(stackLabel);
        setAlignment(stackLabel,Pos.CENTER);
	}

	private void updateLabel(){
        label.setText(String.valueOf(count));
    }

	private void seedFadeOut(){
        FadeTransition ft = new FadeTransition(Duration.millis(500),seeds);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                seeds.getChildren().clear();
            }
        });
    }

    private void seedFadeIn(SeedView s){
        FadeTransition ft = new FadeTransition(Duration.millis(500),s);
        ft.setFromValue(0);
        ft.setToValue(1);
        FillTransition fillT = new FillTransition();
        fillT.setShape(s);
        fillT.setDuration(Duration.millis(300));
        fillT.setToValue(Color.web("#2a282d"));
        FillTransition reverseFillT = new FillTransition();
        reverseFillT.setShape(s);
        reverseFillT.setDuration(Duration.millis(300));
        reverseFillT.setToValue(Color.WHITE);
        SequentialTransition sq = new SequentialTransition();
        sq.getChildren().addAll(ft,fillT,reverseFillT);
        sq.play();
    }

	public void addSeeds(int s){
		for(int i=0;i<s;i++){
			SeedView seed = new SeedView((int) (x+(Math.random()*30)),(int) (y+(Math.random()*30)),10);
            seed.setOpacity(0);
            seeds.getChildren().add(seed);
            count++;
            updateLabel();
            seedFadeIn(seed);
		}
	}

	public void addOneSeed(){
        SeedView seed = new SeedView((int) (x+(Math.random()*30)),(int) (y+(Math.random()*30)),10);
        seed.setOpacity(0);
        seeds.getChildren().add(seed);
        count++;
        updateLabel();
        seedFadeIn(seed);
    }

	public void removeSeeds(){
        count=0;
        updateLabel();
		seedFadeOut();
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int getSize(){
        return count;
    }

	public void setBottom(){
        setTop(null);
        setBottom(stackLabel);
        label.setPadding(new Insets(10,0,0,0));
    }

	public void clear(){
        seeds.getChildren().clear();
        count=0;
        updateLabel();
    }
}
