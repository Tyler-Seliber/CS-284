package shapes;

// class X extends Y (its super class) -- super inheritance
//      implements I1, I2, I3 (various interfaces) -- multiple interface
public class Rectangle extends Shape {
    private double width;
    private double height;

    static int numberOfRectangles = 0;

    // NO return type
    public Rectangle() {
        this(0.0, 0.0); // this(...) DELEGATES to another constructor
        //numberOfRectangles += 1;
    }

    public Rectangle(double initialWidth, double initialHeight) {
        super("yellow");
        numberOfRectangles += 1;

        width = initialWidth;
        height = initialHeight;

//        System.out.println("i'm in the constructor, making a rectangle with width " + width + " and height " + height);
    }

    public double area() {
        return width * height;
    }

    public String getColor() {
        return "yellow";
    }

    // WEIRD-STUFF return-type method-name(arguments)
    public static void main(String[] args) {
        // FUNCTION(arguments)
        System.out.println("hello cs 284-a");

        // instance properties vs. static properties (belong to the class itself)

        Rectangle r;
        r = new Rectangle(1.0, 2.0);
        System.out.println("width is " + r.width);
        System.out.println("height is " + r.height);
        System.out.println("r has area " + r.area());

        System.out.println("there are " + Rectangle.numberOfRectangles + " rectangles out there");

        Rectangle r2 = new Rectangle(); // equivalent to Rectangle(0.0, 0.0)
        System.out.println("r2 width is " + r2.width);
        System.out.println("r2 height is " + r2.height);
        System.out.println("r2 has area " + r2.area());

        System.out.println("there are " + Rectangle.numberOfRectangles + " rectangles out there");

        // booleans
        boolean b = true;
        b = false;
        System.out.println(b);

        // doubles
        double pi = 3.14159;

        // ints
        int radius = 4;

        double area = (int) (pi * (radius * radius)); // the Math module

        // Strings
        String msg = "the circle's integral area is " + area;
        System.out.println(msg);

        // ARRAYS
        // sequences of values of the same type
        int[] a = new int[5];
        System.out.println("a has length " + a.length);
        System.out.println("a[0] is " + a[0]);
        a[3] = 12;

        int[] fact = {1, 1, 2, 6, 24, 120};
        System.out.println("fact has length " + fact.length);
        System.out.println("fact[0] is " + fact[0]);
        System.out.println("fact[5] is " + fact[5]);

//		fact[5] = "one hundred twenty";
//		System.out.println("fact[6] is " + fact[6]);
        double z = area / 0.0;
        System.out.println("z is " + z);

//		int y = ((int) area) / 0;
//		System.out.println("y is " + y);


        int x = 0;
        int q1 = x++; // 0, x = 1
        int q2 = ++x; // 2, x = 2
        System.out.println("x is " + x + " and q1 is " + q1 + " and q2 is " + q2);

        // for (initializer; termination check; incrementor/stepper) { ... }
        for (int i = 0; i < fact.length; i += 1) {
            System.out.println("fact[" + i + "] is " + fact[i]);
        }
        // i = 0
        // runs the print
        // i += 1, i is now 1
        // 1 < fact.length (6), keep going

        // i = 1
        // runs the print
        // i += 1, is now 2
        // 2 < 6, keep going

        // ... many days later

        // i = 5
        // runs the print
        // i += 1, is now 6
        // !(6 < 6), end the loop
//		System.out.println("i is " + i); // i is only in scope in the for loop
    }
}