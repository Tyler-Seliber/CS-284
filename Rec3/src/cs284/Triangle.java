package cs284;

public class Triangle {
    private int a, b, c;

    public Triangle(int a, int b, int c) {
        if (a + b < c) { throw new IllegalArgumentException("violated triangle inequality"); }

        if (a <= 0) { throw new IllegalArgumentException("a was " + a + ", must be positive"); }
        if (b <= 0) { throw new IllegalArgumentException("b was " + b + ", must be positive"); }
        if (c <= 0) { throw new IllegalArgumentException("c was " + c + ", must be positive"); }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int perimeter() {
        return a + b + c;
    }

    public double area() {
        // Heron's formula
        double s = perimeter() / 2;

        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}