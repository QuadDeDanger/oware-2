package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class HouseView extends BorderPane {
	private int count;
	private int x;
	private int y;
    private Group seeds;
    private StackPane stackHouse;
    private StackPane stackLabel;
    private Label label;

	/**
	 * Creates a group that stores the
	 * @param x
	 * @param y
	 */
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

	public void addSeeds(int s){
		for(int i=0;i<s;i++){
			SeedView seed = new SeedView((int) (x+(Math.random()*30)),(int) (y+(Math.random()*30)),10);
            seeds.getChildren().add(seed);
            count++;
		}
		label.setText(String.valueOf(count));
	}

	public void removeSeeds(int s){
		seeds.getChildren().remove(s);
        count--;
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

    public void setText(String s){
        label.setText(s);
    }

	public void clear(){
        seeds.getChildren().clear();
        count=0;
    }
}
