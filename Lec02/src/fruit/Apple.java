package fruit;

import color.Colorable;

public class Apple extends Fruit implements Colorable {
    private String variety;

    public Apple(String variety, Boolean seeds, double weight) {
        super(seeds, weight);
        this.variety = variety;
    }

    public void eatHalf() {
//        this.setWeight(this.getWeight() / 2);
        setWeight(getWeight() / 2); // implicit this is the receiver of a method in python
    }

    public String getVariety() {
        return this.variety;
    }

    @Override // annotation, mark it everytime you implement interface method / override a superclass method
    // just tells the compiler what your intent is
    public String getColor() {
        return "red with a bit of green";
    }
}
