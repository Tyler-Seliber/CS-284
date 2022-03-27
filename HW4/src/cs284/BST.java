/**
 * Name: Tyler Seliber
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

package cs284;

public class BST<E extends Comparable<? super E>> {

    class Node {
        E data;
        Node left;
        Node right;

        Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    Node root;
    int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public int height() {
        // TODO
        return 0;
    }

    public boolean isPerfect() {
        // TODO
        return false;
    }

    public boolean isDegenerate() {
        // TODO
        return false;
    }

    public E insert(E tgt) { // returns old element
        // TODO
        return null;
    }

    public E lookup(E tgt) { // throws exception when not found
        // TODO
        return null;
    }

    public boolean contains(E tgt) {
        // TODO
        return false;
    }

    public E remove(E tgt) { // returns old element (or null if not found)
        // TODO
        return null;
    }

    public static BST<Integer> makeDegenerate(int size) {
        // TODO
        return null;
    }

    public static BST<Integer> makePerfect(int height) {
        // TODO
        return null;
    }
}