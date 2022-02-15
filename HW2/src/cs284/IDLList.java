package cs284;

import java.util.ArrayList;

public class IDLList<E> {
    private class Node<E> {
        E data;
        Node next;
        Node prev;

        public Node(E elem) {
            this.data = elem;
            this.next = null;
            this.prev = null;
        }

        public Node(E elem, Node<E> next, Node<E> prev) {
            this.data = elem;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    public IDLList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.indices = new ArrayList<Node<E>>();
    }

    public boolean add(int index, E elem) {
        // Check if the index is out of bounds
        if (index < 0 || index > size) {
            indexOutOfBounds(index);
        }

        Node<E> newNode = new Node<E>(elem);
        // Check if the list is empty, if so, add the element to the head
        if (size == 0) {
            head = newNode;
            tail = head;
        } else if (index == 0) { // If the index is 0, set the new node as the head
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (index == size) { // If the index is size, set the new node as the tail
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else {
            newNode.next = indices.get(index);
            newNode.prev = indices.get(index - 1);
            newNode.next.prev = newNode;
            newNode.prev.next = newNode;
        }

        indices.add(index, newNode);
        size += 1;
        return true;
    }

    public boolean add(E elem) {
        return add(0, elem);
    }

    public boolean append(E elem) {
        return add(size, elem);
    }

    public E get(int index) {
        return indices.get(index).data;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public int size() {
        return size;
    }

    public E removeFirst() {
        return removeAt(0);
    }

    public E removeLast() {
        return removeAt(size - 1);
    }

    public E removeAt(int index) {
        // TODO
        return null;
    }

    // Removes first occurence of elem from the list
    public boolean remove(E elem) {
        for (int i = 0; i < size; i += 1) {
            if (indices.get(i).data.equals(elem)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i += 1) {
            sb.append(indices.get(i).data);
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Throw IllegalArgumentException with the given error message
    private static void illegalArgs(String error) {
        throw new IllegalArgumentException(error);
    }

    // Throw IndexOutOfBoundsException for the given invalid index
    private static void indexOutOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds");
    }
}
