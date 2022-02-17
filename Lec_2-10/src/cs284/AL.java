package cs284;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

public class AL<E> implements Iterable<E> {
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

    public String get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + " is out of bounds");
        }
        return (String) data[i];
    }

    @Override
    public Iterator<E> iterator() {
        // anonymous inner class
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                E elt = data[index];
                index += 1;
                return elt;
            }
        };
    }


    public static void main(String[] args) {

        AL<String> l = new AL<>();
        l.add("a");
        l.add("b");
        l.add("c");

//        for (int i = 0; i < l.size; i += 1) {
//            System.out.println(l.get(i));
//        }

        for (String s : l) {
            for (String s2 : l) {
                System.out.println(s + s2);
            }
        }

        /** Iterator<String> iter = l.iterator();
         * while (iter.hasNext()) {
         *    System.out.println(iter.next());
         */

//        List<String> ll = new LinkedList<>();
//        ll.add("ll a");
//        ll.add("ll b");
//        ll.add("ll c");
//
//        Iterator<String> iter = ll.iterator();
//        while (iter.hasNext()) {
//            String s = iter.next();
//            System.out.println(s);
//        }
        // Same as:
//        for (String s : ll) {
//            System.out.println(s);
//        }

    }
}



