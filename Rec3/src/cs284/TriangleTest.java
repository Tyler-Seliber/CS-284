package cs284;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TriangleTest {

    @Test
    void rightTriangle345() {
        Triangle t = new Triangle(3, 4, 5);
        assertEquals(t.perimeter(), 12);
        assertEquals(t.area(), 6);
    }


    @Test
    void testInvalidArgs() {
        invalidArgs(0, 0, 0);
        invalidArgs(0, 1, 1);
        invalidArgs(1, 0, 1);
        invalidArgs(1, 1, 0);
        invalidArgs(3, 5, 10);
    }

    void invalidArgs(int a, int b, int c) {
        try {
            Triangle invalidTriangle = new Triangle(a, b, c);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException _e) {
            // expected
        } catch (Throwable t) {
            fail("Unexpected exception: " + t);
        }

    }

}