package cs284;
// Lecture 1/20/22

/* modifiers:
 *
 * public: everyone can see it (in any class, etc.)
 *
 *  ENCAPSULATION: controls who can touch it
 * protected: only the declaring class and the package and subclasses can see it
 * private: only the declaring class can see it
 *
 * static: belongs to the class, not the instance/object
 * final: on variable declaration, can't change the **reference**
 */

/* packages:
 * java.util.Arrays
 * java.lang.(...)
 *
 * kind of like domain names
 * edu.stevens.cs.cs284.Main
 *
 * we will be working with:
 * cs284.Main
 */

public class Main {
    public static void main(String[] args) {
        int[] a = new int[5];
        for (int i = 0; i < a.length; i += 1) {
            System.out.println("a[" + i + "] = " + a[i]);
        }

        // for-each loop
        for (int x : a) {
            System.out.println(x);
        }

        // same as
        for (int i = 0; i < a.length; i += 1) {
            int x = a[i];
            System.out.println(x);
        }

        String[] ss = { "hi", "hello", "howdy" };
        for (String greeting : ss) {
            System.out.println(greeting + ", nice to meet you!");
        }

        // multi-dimensional array
        final int ROWS = 10;
        final int COLS = 10;
        double[][] matrix = new double[ROWS][COLS];
        for (int i = 0; i < ROWS; i += 1) {
            for (int j = 0; j < COLS; j += 1) {
//                ROWS += 1; // compile error
                matrix[i][j] = i * j;
                System.out.println("matrix[" + i + "][" + j + "] = " + matrix[i][j]);
            }
        }

        for (double[] row : matrix) {
            for (double entry : row) {
                System.out.println(entry);
            }
        }

        // aliasing
        final int[] data1 = { 1, 2, 3, 4, 5 };
        int[] data2 = data1; // data2 ALIASES data1; both names refer to the same array
        int[] data3 = data1.clone(); // data3 COPIES data1; both names refer to different arrays
        // or: int[] data3 = Arrays.copyOf(data1, data1.length);
        data2[0] = 8; // data1[0] is now 8, it has been overwritten

        // no compile error because the reference "data1" isn't changed, despite the object being updated

        System.out.println(data1[0]);
        System.out.println(data3[0]);

        // testing private/protected modifier
        Rectangle r = new Rectangle(3.0, 5.0);
        System.out.println(r.width);

        Point2DMkI p = new Point2DMkI();
        p.x = 12;
        p.y = 3.14159;

        Point2DMkII p2 = new Point2DMkII(12, 3.14159);
        Point2DMkIII p3 = new Point2DMkIII(12, 3.14159);
    }
}

// a 'record'
class Point2DMkI{
    public double x;
    public double y;
}

// imutable
class Point2DMkII {
    private double x;
    private double y;

    Point2DMkII(double newX, double newY) {
        x = newX;
        y = newY;
    }

    // getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}

// mutable
class Point2DMkIII {
    private double x;
    private double y;

    /* variable resolution in constructors and instance methods:
     *
     * 1. parameters, local variables
     * 2. fields on this
     *
     * in static methods:
     *
     * 1. parameters, local variables
     * 2. static fields
     */

    // 'this' - exists in constructors and non-static methods
    // aka 'self' in Python
    Point2DMkIII(double x, double y) {
        System.out.println("x is " + x + " and this.x is " + this.x);
        this.x = x; // the "PARAMETER" x SHADOWS the "FIELD" x
        this.y = y;
        // or
//        this.setX(x);
//        this.setY(y);
    }

    // setters
    // useful because you can put extra code in to do more things (logging, validity checking, representation changes (ex take in X and Y, store in polar coords), etc.)
    public void setX(double x) {
//        if (x < this.x) { /* could give an error, x can only increase */ };
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    // getters
    public double getX() {
        Point2DMkIII myself = this;
        return x;
    }

    public double getY() {
        return y;
    }

}