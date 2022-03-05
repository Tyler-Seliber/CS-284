/**
 * Name: Tyler Seliber
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

package cs284;

public class BinarySearch {

    public static <E extends Comparable<? super E>> int binarySearch(E elt, E[] a) {
        if (a.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        return binarySearch(elt, a, 0, a.length);
    }

    private static <E extends Comparable<? super E>> int binarySearch(E elt, E[] a, int lo, int hi) {
        if (lo >= hi) {
            return -1;
        }

        int mid = lo + (hi - lo) / 2;
        E x = a[mid];

        // If elt < x, search the left half
        if (elt.compareTo(x) < 0) {
            return binarySearch(elt, a, lo, mid);
        }

        // If elt > x, search the right half
        if (elt.compareTo(x) > 0) {
            return binarySearch(elt, a, mid + 1, hi);
        }

        // If elt is found (elt.compareTo(x) == 0), return mid
        return mid;
    }

}
