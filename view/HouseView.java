package view;

import javafx.scene.Group;
import java.util.ArrayList;

public class HouseView extends Group {
	//Used to store seeds that exist in this house
	private int count;
	/*Location stored to for seeds that need to move to this house.
	Location sent with getX and getY methods*/
	private int x;
	private int y;

	/*
	Creates house group that can store seeds inside
	*/
	public HouseView(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.count = 0;
	}

	/*
	Adds seed to the house group to make it easy to display
	*/
	public void addSeeds(SeedView s) {
		getChildren().add(s);
	}

	public void addSeeds(int s){
		for(int i=0;i<s;i++){
			SeedView seed = new SeedView((int) (x+(Math.random()*30)),(int) (y+(Math.random()*30)),10);
            getChildren().add(seed);
		}
	}

	//Removes seeds from the group
	public void removeSeeds(SeedView s){
		getChildren().remove(s);
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

}
