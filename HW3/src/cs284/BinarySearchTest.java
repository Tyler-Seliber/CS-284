package cs284;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    @org.junit.jupiter.api.Test
    void binarySearch() {
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(0, BinarySearch.binarySearch(1, a));
        assertEquals(1, BinarySearch.binarySearch(2, a));
        assertEquals(2, BinarySearch.binarySearch(3, a));
        assertEquals(3, BinarySearch.binarySearch(4, a));
        assertEquals(4, BinarySearch.binarySearch(5, a));
        assertEquals(5, BinarySearch.binarySearch(6, a));
        assertEquals(6, BinarySearch.binarySearch(7, a));
        assertEquals(7, BinarySearch.binarySearch(8, a));
        assertEquals(8, BinarySearch.binarySearch(9, a));
        assertEquals(9, BinarySearch.binarySearch(10, a));
        assertEquals(-1, BinarySearch.binarySearch(11, a));

        Character[] b = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        assertEquals(0, BinarySearch.binarySearch('a', b));
        assertEquals(1, BinarySearch.binarySearch('b', b));
        assertEquals(2, BinarySearch.binarySearch('c', b));
        assertEquals(3, BinarySearch.binarySearch('d', b));
        assertEquals(4, BinarySearch.binarySearch('e', b));
        assertEquals(5, BinarySearch.binarySearch('f', b));
        assertEquals(6, BinarySearch.binarySearch('g', b));
        assertEquals(7, BinarySearch.binarySearch('h', b));
        assertEquals(8, BinarySearch.binarySearch('i', b));
        assertEquals(9, BinarySearch.binarySearch('j', b));
        assertEquals(-1, BinarySearch.binarySearch('k', b));

        Integer[] c = new Integer[0];
        try {
            BinarySearch.binarySearch(1, c);
            fail("Should have thrown an exception");
        } catch (IllegalArgumentException e) {
            // expected
        } catch (Exception e) {
            fail("Unknown exception: " + e);
        }

    }
}