package cs284;

public class RLL<E> {
    class Node {
        E data;
        Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head;

    public RLL() {
        head = null;
    }

    public int size() {
        return size(head);
    }

    private int size(Node n) {
        if (head == null) {
            return 0;
        }
        return 1 + size(n.next);
    }

    public E get(int index) {
        return get(head, index);
    }

    private E get(Node n, int index) {
        if (n == null) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return n.data;
        }
        return get(n.next, index - 1);
    }

    private E getShorty(Node n, int index) {
        if (n == null) {
            throw new IndexOutOfBoundsException();
        }
        return index == 0 ? n.data : getShorty(n.next, index - 1);

    }

}
