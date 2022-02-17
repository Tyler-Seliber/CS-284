package cs284;

import java.util.Iterator;

public class LL<E> implements Iterable {

    private Node head; // null means the empty list

    public LL() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int size = 0;
        for (Node n = head; n != null; n = n.next) {
            size += 1;
        }
        return size;
    }

    private Node getNode(int index) {
        int i = 0;
        for (Node n = head; n != null; n = n.next) {
            if (i == index) {
                return n;
            }
            i += 1;
        }
        throw new IndexOutOfBoundsException("There is no " + index + "in a list of length " + i);
    }

    private void addFirst(E e) { // O(1)
        Node n = new Node();
        n.data = e;
        n.next = this.head;
        this.head = n;
    }

    private void addAfter(Node n, E e) {
        // create a node
        Node nNew = new Node();
        nNew.data = e;
        // link it up properly
        nNew.next = n.next;
        // make sure that it's referenced
        n.next = nNew;
    }

    public boolean add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else {
            // look up the index-1 node
            Node prev = getNode(index - 1);
            // add e right after
            addAfter(prev, e);
        }
        return true;
    }

    public E get(int index) {
        return getNode(index).data;
    }

    public E set(int index, E newVal) {
        Node n = getNode(index);
        E oldVal = n.data;
        n.data = newVal;
        return oldVal;
    }

    public E remove(int index) { // very similar to add
        return null;
    }

    class I implements Iterator<E> {
        private Node cur = head;

        @Override
        public boolean hasNext() {
            // has Next when cur (current node) is not null
            return cur != null;
        }

        @Override
        public E next() {
            E elt = cur.data;
            cur = cur.next;
            return elt;
        }
    }

    @Override
    public Iterator iterator() {
        return new I();
    }

    // Inner class
    // As an inner class, Node knows about type E so you don't need to re-parameterize it
    class Node { // note: no public (private not needed)
        private E data;
        private Node next;
    }

    public static void main(String[] args) {
        LL<String> l = new LL<>();
        l.add(0, "a");
        l.add(1, "b");
        l.add(2, "c");

        // for loop is "accidentally quadratic"
//        for (int i = 0; i < l.size(); i++) {
//            System.out.println(l.get(i));
//        }

        for (Object s : l) { // should be String instead of Object
            System.out.println(s);
        }
    }
}
