package cs284;

import java.util.Arrays;

public class AL<E> {
    private static final int INITIAL_CAPACITY = 64; // "final" defines a constant, can't be changed
    private E[] data; // data.length is CAPACITY
    private int size = 0; // how much is currently used

    public AL() {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void reallocate() {
        data = Arrays.copyOf(data, data.length * 2);
    }

    public boolean add(E e) {
        if (size == data.length) {
            reallocate();
        }

        data[size] = e;
        size += 1;

        return true;
    }
}
