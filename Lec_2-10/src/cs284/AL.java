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

    public void add(int index, E e) {
        if (size == data.length) {
            reallocate();
        }

        // first, everybody move over
        for (int i = size - 1; i >= size; i -= 1) {
            data[i + 1] = data[i];
        }

        // then, write the element in
        data[index] = e;
        size += 1;
    }
}
