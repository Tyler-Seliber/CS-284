package shapes;

import color.Colorable;

// abstract class = can't be directly instantiated
public abstract class Shape implements Colorable {
    private String color;

    protected Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    // abstract method = no definition yet
    public abstract double area();


}
