package cs284;

public class Rectangle {
    protected double width;
    private double height;

    static int numberOfRectangles = 0;

    // NO return type
    public Rectangle() {
        this(0.0, 0.0); // this(...) DELEGATES to another constructor
//        System.out.println("i'm in the constructor, making a rectangle");
    }

    public Rectangle(double initialWidth, double initialHeight) {
        numberOfRectangles += 1;
        width = initialWidth;
        height = initialHeight;
        System.out.println("i'm in the constructor, making a rectangle with width " + width + " and height " + height);
    }

    public static void main(String[] args) {
        System.out.println("hello cs 284-a");

        // instance properties vs. static properties (belong to the class)

        Rectangle r;
        r = new Rectangle(1.0, 2.0);
        System.out.println("width is " + r.width);
        System.out.println("height is " + r.height);

        System.out.println("there are " + Rectangle.numberOfRectangles + " rectangles out there");

        Rectangle r2 = new Rectangle();
        System.out.println("r2 width is " + r2.width);
        System.out.println("r2 height is " + r2.height);

        System.out.println("there are " + Rectangle.numberOfRectangles + " rectangles out there");

        // booleans
        boolean b = true;
        b = false;
        System.out.println(b);

        // doubles
        double pi = 3.14159;

        // ints
        int radius = 4;

        double area = pi * (radius * radius); //the Math module
        System.out.println("the circle's area is " + area);

        // Strings
        String msg = "the circle's integral area is " + area;
        System.out.println(msg);

        // ARRAYS
        // sequences of values of the same type
        int[] a = new int[5];
        System.out.println("a has length " + a.length);
        System.out.println("a[0] is " + a[0]);

        int[] fact = { 1, 1, 2, 6, 24, 120 };
        System.out.println("fact has length " + fact.length);
        System.out.println("fact[0] is " + fact[0]);
        System.out.println("fact[5] is " + fact[5]);

        // type checker
//        fact[5] = "one hundred twenty";
//        flact[5] = 121;
//        System.out.println("fact[6] is " + fact[6]);

//        double z = area / 0.0; // becomes positive infinity in Java
//        // the above line gives a warning that z isn't used, but the compiler ignores it
//        System.out.println("z is " + z);
//        int y = (int) area/0; // this on its own will not cause an error with the compiler
//        System.out.println("y is " + y);

        // incrementor
        int x = 0;
        int q1 = x++; // 0, x = 1
        int q2 = ++x; // 2, x = 2
        // try to avoid this notation, use += 1 instead

        // for loops
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
        // i += 1, i is now 2
        // 2 < fact.length (6), keep going

        // ...

        // i = 5
        // runs the print
        // i += 1, i is now 6
        // 6 < fact.length (6), stop

//        System.out.println("i is " + i); // gives error, i is only in scope in the for loop
    }
}
