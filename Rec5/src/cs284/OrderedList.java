package cs284;

import java.util.Iterator;
import java.util.LinkedList;

public class OrderedList<E extends Comparable<E>> implements Iterable<E> {
    private LinkedList<E> ol;

    public OrderedList() {
        ol = new LinkedList<>();
    }

    public boolean isEmpty() {
        return ol.isEmpty();
    }

    public int size() {
        return ol.size();
    }

    public E get(int index) {
        return ol.get(index);
    }

    public Iterator<E> iterator() {
        public Iterator<E> iterator () {
            // anonymous inner class
            return new Iterator<E>() {
                private int index = 0;

                @Override
                public boolean hasNext() {
                    return index < size();
                }

                @Override
                public E next() {
                    E elt = get(index);
                    index += 1;
                    return elt;
                }
            };
        }
    }

    public int insert(E data) {
        int index = 0;
        for (E elt : ol) {
            if (data.compareTo(elt) < 0) {
                ol.add(index, data);
            }
        }
        return index;
    }

    public static void main(String[] args) {
        OrderedList<String> l = new OrderedList<>();
        l.insert("c");
        l.insert("a");
        l.insert("b");

        for (String s : l) {
            System.out.println(s);
        }
    }
}
