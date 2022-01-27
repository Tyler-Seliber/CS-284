package shapes;

public class PlayingWithShapes {
    public static void main(String[] args) {
        Shape[] shapes = {new Rectangle(), new Rectangle(1.7, 7.2), new Circle(3.4)};

        int i = 0;
        for (Shape s : shapes) {
            System.out.println("Shape " + i + " has area " + s.area() + " and color " + s.getColor());
            i += 1;

            if (s instanceof Circle) { //in python: isinstance
                Circle c = (Circle) s; //downcast = UNSAFE
                System.out.println("It's a circle, with radius " + c.getRadius());

            }

            try {
                Circle c = new Circle(-1.3);
                System.out.print("Circle 0 has area " + c.area());
            } catch (Throwable e) {
                System.out.println("alas, poor circle, i knew him well.  " + e.getMessage());
            }
        }
    }
}

//              Throwable
//      Error___/      \___Exception <-- checked by default
//                          \___RuntimeException <--- unchecked
//                              \___IllegalArgumentException
//