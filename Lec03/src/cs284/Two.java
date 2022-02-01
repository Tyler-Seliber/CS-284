package cs284;

import java.util.Objects;

public class Two<T> /* Type parameter allows arbitrary object types */ {

    private T first, second;

    public Two(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }

    public T second() {
        return second;
    }

    public int length() {
        return first.toString().length() + second.toString().length();
    }

    public static void main(String[] args) {
        Two<Integer> tInt = new Two<Integer>(5, 6);
        int theFirst = tInt.first();
        System.out.println("first one is " + theFirst);
        System.out.println("second one is " + tInt.second());

        Two<String> tString = new Two<String>("hi", "what's up");
        System.out.println("first one is " + tString.first());
        System.out.println("second one is " + tString.second());

        Pair<String, Boolean> p = new Pair<>("wow", false);
//        boolean oopsie = p.first;
        String ohyeah = p.first();
        // java.util.List
        System.out.println("our p is: " + p);

        String s1 = "hello";
        String s2 = "he";
        s2 += "l";
        s2 += "l";
        s2 += "o";
        String s3 = "hello";

        System.out.println("==:\t" + (s1 == s3)); // reference equality - are both things referencing the same piece of memory?
        System.out.println(".equals:\t" + s1.equals(s3)); // structural equality
    }
}

class Pair<F, S> {
    private F first;
    private S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F first() {
        return first;
    }

    public S second() {
        return second;
    }

    @Override
    public String toString() {
        // good for debugging
//        return "Pair(" + first.toString() + ", " + second.toString() + ")";
        return "(" + first.toString() + ", " + second.toString() + ")";
    }

    // if ==, then must be .equals
    // should be an equivalence (a.equals(b) == b.equals(a))
    // should be transitive (a.equals(b) && b.equals(c) => a.equals(c))
    // If you override .equals you must override .hashCode
    @Override
    public boolean equals(Object o) {
        if (o instanceof Pair) { // Pair doesn't take type parameters because instanceof is a runtime check
            Pair<Object, Object> other = (Pair<Object, Object>) o; // safe case bc we know O is a Pair
            return this.first.equals(other.first) && this.second.equals(other.second);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second); // perfect default implementation
    }

}