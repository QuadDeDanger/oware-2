package functionality;

/**
 * Created by Haaris on 08/11/2016.
 */
public class Player {

    private String name;
    private House house;

    public Player(String name) {
        this.name = name;
        house = new House();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public House getHouse() {
        return house;
    }

    public int getScore() {
        return house.getCount();
    }

    public void addSeedToHouse(Seed seed) {
        house.addSeed(seed);
    }


}
