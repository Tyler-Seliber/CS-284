package cs284;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    @Test
    void testInsert() {
        BST<Integer> bst = new BST<>();
        assertEquals(0, bst.size());
        assertEquals(0, bst.height());
        assertFalse(bst.contains(0));
        try {
            bst.lookup(0);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) { /* Expected */ }


        // Insert 3
        //        3
        assertNull(bst.insert(3));
        assertEquals(3, bst.lookup(3));
        assertTrue(bst.contains(3));
        assertEquals(1, bst.size());
        assertEquals(1, bst.height());


        // Insert 1
        //        3
        //       /
        //      1
        assertNull(bst.insert(1));
        assertEquals(1, bst.lookup(1));
        assertTrue(bst.contains(1));
        assertEquals(2, bst.size());
        assertEquals(2, bst.height());

        // Insert 5
        //        3
        //       / \
        //      1   5
        assertNull(bst.insert(5));
        assertEquals(5, bst.lookup(5));
        assertTrue(bst.contains(5));
        assertEquals(3, bst.size());
        assertEquals(2, bst.height());

        // Insert 2
        //        3
        //       / \
        //      1   5
        //       \
        //        2
        assertNull(bst.insert(2));
        assertEquals(2, bst.lookup(2));
        assertTrue(bst.contains(2));
        assertEquals(4, bst.size());
        assertEquals(3, bst.height());

        // Insert 4
        //        3
        //       / \
        //     1     5
        //      \   /
        //       2 4
        assertNull(bst.insert(4));
        assertEquals(4, bst.lookup(4));
        assertTrue(bst.contains(4));
        assertEquals(5, bst.size());
        assertEquals(3, bst.height());

        // Insert 7
        //        3
        //       / \
        //     1     5
        //      \   / \
        //       2 4   7
        assertNull(bst.insert(7));
        assertEquals(7, bst.lookup(7));
        assertTrue(bst.contains(7));
        assertEquals(6, bst.size());
        assertEquals(3, bst.height());

        assertFalse(bst.contains(0));
        try {
            bst.lookup(0);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) { /* Expected */ }

    }

    @Test
    void testRemove() {
        BST<Integer> bst = new BST<>();

        // Test removing from an empty tree
        assertNull(bst.remove(0));

        // Generate the same tree as in testInsert()
        bst.insert(3);
        bst.insert(1);
        bst.insert(5);
        bst.insert(2);
        bst.insert(4);
        bst.insert(7);

        // Remove 4
        //        3
        //       / \
        //     1     5
        //      \     \
        //       2     7
        assertEquals(4, bst.remove(4));
        assertEquals(5, bst.size());
        assertEquals(3, bst.height());
        assertFalse(bst.contains(4));
        try {
            bst.lookup(4);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) { /* Expected */ }

        // Remove 5
        //        3
        //       / \
        //     1     7
        //      \
        //       2
        assertEquals(5, bst.remove(5));
        assertEquals(4, bst.size());
        assertEquals(3, bst.height());
        assertFalse(bst.contains(5));
        try {
            bst.lookup(5);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) { /* Expected */ }

        // Remove 3
        //        1
        //       / \
        //     2     7
        assertEquals(3, bst.remove(3));
        assertEquals(3, bst.size());
        assertEquals(2, bst.height());
        assertFalse(bst.contains(3));
        try {
            bst.lookup(3);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) { /* Expected */ }

        // Test removing an element that is not in the tree
        assertNull(bst.remove(6));
        assertEquals(3, bst.size());
        assertEquals(2, bst.height());


        // Remove 1
        //        7
        //       /
        //     2
        assertEquals(1, bst.remove(1));
        assertEquals(2, bst.size());
        assertEquals(2, bst.height());
        assertFalse(bst.contains(1));
        try {
            bst.lookup(1);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) { /* Expected */ }

        // Remove 2
        //        7
        assertEquals(2, bst.remove(2));
        assertEquals(1, bst.size());
        assertEquals(1, bst.height());
        assertFalse(bst.contains(2));
        try {
            bst.lookup(2);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) { /* Expected */ }

        // Remove 7
        assertEquals(7, bst.remove(7));
        assertEquals(0, bst.size());
        assertEquals(0, bst.height());
        assertFalse(bst.contains(7));
        try {
            bst.lookup(7);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) { /* Expected */ }

        // Test removing from an empty tree
        assertNull(bst.remove(0));
    }

    @Test
    void isPerfect() {
        // Generate this tree:
        //             4
        //            / \
        //          2     6
        //         / \   / \
        //        1   3 5   7
        BST<Integer> bst = new BST<>();

        bst.insert(4);
        assertFalse(bst.isPerfect());

        bst.insert(2);
        assertFalse(bst.isPerfect());

        bst.insert(6);
        assertTrue(bst.isPerfect());

        bst.insert(3);
        assertFalse(bst.isPerfect());

        bst.insert(7);
        assertFalse(bst.isPerfect());

        bst.insert(1);
        assertFalse(bst.isPerfect());

        bst.insert(5);
        assertTrue(bst.isPerfect());

        bst.insert(8);
        assertFalse(bst.isPerfect());

        bst.remove(8);
        assertTrue(bst.isPerfect());


    }

    @Test
    void isDegenerate() {
        BST<Integer> bst = new BST<>();

        bst.insert(4);
        assertTrue(bst.isDegenerate());

        bst.insert(6);
        assertTrue(bst.isDegenerate());

        bst.insert(2);
        assertFalse(bst.isDegenerate());

        bst.remove(2);
        assertTrue(bst.isDegenerate());

        bst.insert(8);
        assertTrue(bst.isDegenerate());

        bst.insert(9);
        assertTrue(bst.isDegenerate());

        bst.insert(10);
        assertTrue(bst.isDegenerate());

    }

    @Test
    void makeDegenerate() {
        BST<Integer> degenerateTree1 = BST.makeDegenerate(1);
        assertTrue(degenerateTree1.isDegenerate());

        BST<Integer> degenerateTree2 = BST.makeDegenerate(2);
        assertTrue(degenerateTree2.isDegenerate());

        BST<Integer> degenerateTree3 = BST.makeDegenerate(3);
        assertTrue(degenerateTree3.isDegenerate());

        BST<Integer> degenerateTree7 = BST.makeDegenerate(7);
        assertTrue(degenerateTree7.isDegenerate());
    }

    @Test
    void makePerfect() {
        BST<Integer> perfectTree1 = BST.makePerfect(1);
        assertTrue(perfectTree1.isPerfect());

        BST<Integer> perfectTree2 = BST.makePerfect(2);
        assertTrue(perfectTree2.isPerfect());

        BST<Integer> perfectTree3 = BST.makePerfect(3);
        assertTrue(perfectTree3.isPerfect());

        BST<Integer> perfectTree7 = BST.makePerfect(7);
        assertTrue(perfectTree7.isPerfect());
    }

}
}