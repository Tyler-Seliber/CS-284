package fruit;

public abstract class Fruit {
    // data fields
    private boolean seeds;
    private double weight;

    // Constructor
    public Fruit(boolean seeds, double weight) {
        this.seeds = seeds;
        this.weight = weight;
    }

    // Methods
    public boolean getSeeds() {
        return seeds;
    }

    public void setSeeds(boolean seeds) {
        System.out.println("in Fruit.setSeeds");
        this.seeds = seeds;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
