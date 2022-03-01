package cs284;

import java.util.Stack;

public class Factorial {
    public static int fact(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * fact(n - 1);
        }
    }

    public static int fact2(int n) {
        // ternary operator: if-then-else as an expression
        // cond ? then : else
        return n == 0 ? 1 : n * fact2(n - 1);
    }

    public static void yolo(int n, int m, int q) {
        System.out.println(n);
        yolo(n + 1, m - 1, q + 2);
    }

    public static void main(String[] args) {
        System.out.println(fact(5));
        System.out.println(fact2(5));

//        System.out.println(fact(-1));
        try {
            yolo(0, 0, 0);
        } catch (StackOverflowError e) {
            System.out.println("alas, poor Yorick");
        }
    }

}
