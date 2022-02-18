/*
 * Name: Tyler Seliber
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

package cs284;

import java.util.ArrayList;

public class IDLList<E> {
    private class Node {
        E data;
        Node next;
        Node prev;

        Node(E elem) {
            this.data = elem;
            this.next = null;
            this.prev = null;
        }

        Node(E elem, Node next, Node prev) {
            this.data = elem;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node head;
    private Node tail;
    private int size;
    private ArrayList<Node> indices;

    public IDLList() {
        head = null;
        tail = null;
        size = 0;
        indices = new ArrayList<>();
    }

    public boolean add(int index, E elem) {
        // Check if the index is out of bounds
        checkIndex(index);

        // Create a reference to a new node
        Node newNode;

        // Check if the list is empty, if so, add the element to the head
        if (size == 0) {
            newNode = new Node(elem);
            head = newNode;
            tail = head;
        } else if (index == 0) { // If the index is 0, set the new node as the head
            newNode = new Node(elem, head, null);
            head.prev = newNode;
            head = newNode;
        } else if (index == size) { // If the index is size, set the new node as the tail
            newNode = new Node(elem, null, tail);
            tail.next = newNode;
            tail = newNode;
        } else { // If the index is in the middle, set the new node as the next node after the index
            newNode = new Node(elem, indices.get(index), indices.get(index - 1));
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
        // Check if index is out of bounds
        checkIndex(index);

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
        // Check if index is out of bounds
        checkIndex(index);

        //Check if the list is empty
        if (size == 0) {
            indexOutOfBounds(index);
        } else if (index == 0) { // Removing the head
            if (size == 1) { // If the list is of size 1, set the head and tail to null
                head = null;
            } else { // If the list is of size > 1, set the head to the next node
                head = indices.get(index + 1);
                indices.get(index + 1).prev = null;
            }
        } else if (index == size - 1) { // Removing the tail
            tail = indices.get(index - 1);
            // No need to check if the list is of size 1, since removing the last element would be the same as removing the first
        } else { // Removing an element in the middle
            indices.get(index - 1).next = indices.get(index + 1);
            indices.get(index + 1).prev = indices.get(index - 1);
        }

        E removed = indices.remove(index).data;
        size -= 1;

        return removed;
    }

    // Removes first occurrence of elem from the list
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
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Check for IndexOutOfBounds
    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            indexOutOfBounds(index);
        }
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
