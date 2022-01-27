package fruit;

import color.Colorable;

public class Banana extends Fruit implements Colorable {
    public Banana(double weight) {
        super(false, weight);
    }

    // annotation goes on: overridden parent class method
    @Override
    public void setSeeds(boolean _seed) { //silently ignore it, seedless bananas
        System.out.println("in the overridden method, no seeds");
    }

    // annotation goes on: implemented interface methods
    @Override
    public String getColor() {
        return "yellow";
    }

    public static void main(String[] args) {
        Fruit[] fruitBasket = {new Apple("Ashmead's Kernel", true, 0.25),
                new Banana(0.1),};
        for (Fruit f : fruitBasket) {
            f.setSeeds(true);
            System.out.println("does it have seeds? " + f.getSeeds());
        }
    }
}


