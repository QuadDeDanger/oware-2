package functionality;

/**
 * Created by Haaris on 08/11/2016.
 */
public class Player {

    private String name;
    private ScoreHouse scoreHouse;

    public Player(String name) {
        this.name = name;
        scoreHouse = new ScoreHouse();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ScoreHouse getHouse() {
        return scoreHouse;
    }

    public int getScore() {
        return scoreHouse.getCount();
    }

    public void addSeedToHouse(Seed seed) {
        scoreHouse.addSeed(seed);
    }


}
