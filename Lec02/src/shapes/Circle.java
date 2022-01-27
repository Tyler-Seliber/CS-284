package shapes;

//import color.Colorable;

public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        super("black"); // first line of every constructor is implicitly a delegation to super()

        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive"); // "throw" ~ "raise" in Python
        }

        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

//    public String getColor() {return "black"; }
}