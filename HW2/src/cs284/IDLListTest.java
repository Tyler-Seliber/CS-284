package cs284;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IDLListTest {

    IDLList<String> list1 = new IDLList<>();
    IDLList<String> list2 = new IDLList<>();

    @Test
    void testConstructor() {
        assertEquals(0, list1.size());
        assertEquals("[]", list1.toString());
    }

    @Test
    void testAppend() {
        // Test append to empty list
        assertTrue(list1.append("quick"));
        assertEquals(1, list1.size());
        assertEquals("[quick]", list1.toString());

        assertTrue(list1.append("fox"));
        assertEquals(2, list1.size());
        assertEquals("[quick, fox]", list1.toString());
    }

    @Test
    void testAdd() {
        // Test add at beginning of empty list
        assertTrue(list1.add("quick"));
        assertEquals(1, list1.size());
        assertEquals("[quick]", list1.toString());

        // Test add at index = size
        assertTrue(list1.add(1, "fox"));
        assertEquals(2, list1.size());
        assertEquals("[quick, fox]", list1.toString());

        // Test add at beginning of non-empty list
        assertTrue(list1.add("the"));
        assertEquals(3, list1.size());
        assertEquals("[the, quick, fox]", list1.toString());

        // Test add at index
        assertTrue(list1.add(2, "brown"));
        assertEquals(4, list1.size());
        assertEquals("[the, quick, brown, fox]", list1.toString());


        // Test exceptions
        try {
            list1.add(6, "the");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException _e) {
            // expected
        } catch (Throwable t) {
            fail("Unexpected exception: " + t);
        }

        try {
            list1.add(-1, "dog");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException _e) {
            // expected
        } catch (Throwable t) {
            fail("Unexpected exception: " + t);
        }
    }

    @Test
    void testGet() {
        list1 = generateList();
        assertEquals("quick", list1.get(1));
        assertEquals("fox", list1.get(3));
        assertEquals("lazy", list1.get(list1.size() - 2));

        // Test exceptions
        try {
            list1.get(9);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException _e) {
            // expected
        } catch (Throwable t) {
            fail("Unexpected exception: " + t);
        }

        try {
            list1.get(-1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException _e) {
            // expected
        } catch (Throwable t) {
            fail("Unexpected exception: " + t);
        }
    }

    @Test
    void testGetFirst() {
        list1 = generateList();
        assertEquals("the", list1.getFirst());
    }

    @Test
    void testGetLast() {
        list1 = generateList();
        assertEquals("dog", list1.getLast());
    }

    @Test
    void testSize() {
        list1 = generateList();
        assertEquals(9, list1.size());
    }

    @Test
    void testRemoveFirst() {
        list1 = generateList();
        assertEquals("the", list1.removeFirst());
        assertEquals(8, list1.size());
        assertEquals("[quick, brown, fox, jumps, over, the, lazy, dog]", list1.toString());
    }

    @Test
    void testRemoveLast() {
        list1 = generateList();
        assertEquals("dog", list1.removeLast());
        assertEquals(8, list1.size());
        assertEquals("[the, quick, brown, fox, jumps, over, the, lazy]", list1.toString());
    }

    @Test
    void testRemoveAt() {
        list1 = generateList();

        assertEquals("brown", list1.removeAt(2));
        assertEquals(8, list1.size());
        assertEquals("[the, quick, fox, jumps, over, the, lazy, dog]", list1.toString());

        assertEquals("lazy", list1.removeAt(list1.size() - 2));
        assertEquals(7, list1.size());
        assertEquals("[the, quick, fox, jumps, over, the, dog]", list1.toString());

        // Test exceptions
        try {
            list1.removeAt(9);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException _e) {
            // expected
        } catch (Throwable t) {
            fail("Unexpected exception: " + t);
        }

        try {
            list1.removeAt(-7);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException _e) {
            // expected
        } catch (Throwable t) {
            fail("Unexpected exception: " + t);
        }

        try {
            list1.removeAt(-1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException _e) {
            // expected
        } catch (Throwable t) {
            fail("Unexpected exception: " + t);
        }
    }

    @Test
    void testRemove() {
        list1 = generateList();

        // Test removing first instance
        assertTrue(list1.remove("the"));
        assertEquals(8, list1.size());
        assertEquals("[quick, brown, fox, jumps, over, the, lazy, dog]", list1.toString());

        // Test removing next instance
        assertTrue(list1.remove("the"));
        assertEquals(7, list1.size());
        assertEquals("[quick, brown, fox, jumps, over, lazy, dog]", list1.toString());

        // Test attempting to remove something not in list
        assertFalse(list1.remove("the"));
        assertFalse(list1.remove("jumped"));
        assertEquals(7, list1.size());
        assertEquals("[quick, brown, fox, jumps, over, lazy, dog]", list1.toString());

    }

    @Test
    void testToString() {
    }

    IDLList<String> generateList() {
        IDLList<String> list = new IDLList<>();
        String[] words = {"the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};
        for (String s : words) {
            list.append(s);
        }
        return list;
    }
}