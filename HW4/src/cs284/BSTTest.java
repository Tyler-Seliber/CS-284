package cs284;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    @Test
    void size() {
        BST<Integer> bst = new BST<>();
        assertEquals(0, bst.size());

        bst.insert(1);
        assertEquals(1, bst.size());
        bst.insert(2);
        assertEquals(2, bst.size());

        bst.remove(2);
        assertEquals(1, bst.size());
        bst.remove(1);
        assertEquals(0, bst.size());
    }

    @Test
    void height() {
        BST<Integer> bst = new BST<>();
        assertEquals(0, bst.height());

        bst.insert(1);
        assertEquals(1, bst.height());
        bst.insert(2);
        assertEquals(2, bst.height());
        bst.insert(3);
        assertEquals(2, bst.height());
        bst.insert(4);
        assertEquals(3, bst.height());

        bst.remove(4);
        assertEquals(2, bst.height());
        bst.remove(3);
        assertEquals(2, bst.height());
        bst.remove(2);
        assertEquals(1, bst.height());
        bst.remove(1);
        assertEquals(0, bst.height());
    }

    @Test
    void isPerfect() {
    }

    @Test
    void isDegenerate() {
    }

    @Test
    void insert() {
        BST<Integer> bst = new BST<>();

        assertNull(bst.insert(2));
        assertEquals(2, bst.lookup(2));
        assertEquals(1, bst.size());

        assertEquals(2, bst.insert(1));
        assertEquals(1, bst.lookup(1));
        assertEquals(2, bst.lookup(2));

        assertNull(bst.insert(4));
        assertEquals(4, bst.lookup(4));
        assertEquals(3, bst.size());
        



    }

    @Test
    void lookup() {
    }

    @Test
    void contains() {
    }

    @Test
    void remove() {
    }

    @Test
    void makeDegenerate() {
    }

    @Test
    void makePerfect() {
    }
}