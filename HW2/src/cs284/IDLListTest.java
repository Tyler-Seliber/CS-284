package cs284;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IDLListTest {

    IDLList<String> list1 = new IDLList<>();
    IDLList<String> list2;

    @Test
    void testConstructor() {
        assertEquals(0, list1.size());
        assertEquals("[]", list1.toString());
    }

    @Test
    void testAppend() {
        assertTrue(list1.append("quick"));
        assertTrue(list1.append("fox"));
        assertEquals(2, list1.size());
        assertEquals("[quick, fox]", list1.toString());
    }

    @Test
    void testAdd() {
        // Test add at beginning
        assertTrue(list1.add("quick"));
        // Test add at index
        assertTrue(list1.add(1, "fox"));
        assertEquals(2, list1.size());
        assertEquals("[quick, fox]", list1.toString());

        // Test add at beginning
        assertTrue(list1.add("the"));
        assertEquals(3, list1.size());
        assertEquals("[the, quick, fox]", list1.toString());

        // Test add at index
        assertTrue(list1.add(2, "brown"));
        assertEquals(4, list1.size());
        assertEquals("[the, quick, brown, fox]", list1.toString());
    }

    @Test
    void testGet() {
        list2 = generateList();
        assertEquals("quick", list2.get(1));
        assertEquals("fox", list2.get(3));
        assertEquals("lazy", list2.get(list2.size() - 2));
    }

    @Test
    void testGetFirst() {
        list2 = generateList();
        assertEquals("the", list2.getFirst());
    }

    @Test
    void testGetLast() {
        list2 = generateList();
        assertEquals("dog", list2.getLast());
    }

    @Test
    void testSize() {
        list2 = generateList();
        assertEquals(9, list2.size());
    }

    @Test
    void testRemoveFirst() {
        list2 = generateList();
        assertEquals("the", list2.removeFirst());
        assertEquals(8, list2.size());
        assertEquals("[quick, brown, fox, jumps, over, the, lazy, dog]", list2.toString());
    }

    @Test
    void testRemoveLast() {
        list2 = generateList();
        assertEquals("dog", list2.removeLast());
        assertEquals(8, list2.size());
        assertEquals("[the, quick, brown, fox, jumps, over, the, lazy]", list2.toString());
    }

    @Test
    void testRemoveAt() {
        list2 = generateList();

        assertEquals("brown", list2.removeAt(2));
        assertEquals(8, list2.size());
        assertEquals("[the, quick, fox, jumps, over, the, lazy, dog]", list2.toString());

        assertEquals("lazy", list2.removeAt(list2.size() - 2));
        assertEquals(7, list2.size());
        assertEquals("[the, quick, fox, jumps, over, the, dog]", list2.toString());
    }

    @Test
    void testRemove() {
        list2 = generateList();

        assertTrue(list2.remove("the"));
        assertEquals(8, list2.size());
        assertEquals("[quick, brown, fox, jumps, over, the, lazy, dog]", list2.toString());

        assertTrue(list2.remove("the"));
        assertEquals(7, list2.size());
        assertEquals("[quick, brown, fox, jumps, over, lazy, dog]", list2.toString());

        assertFalse(list2.remove("the"));
        assertFalse(list2.remove("jumped"));
        assertEquals(7, list2.size());
        assertEquals("[quick, brown, fox, jumps, over, lazy, dog]", list2.toString());

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