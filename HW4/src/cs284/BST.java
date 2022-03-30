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

    private Node root;

    public BST() {
        root = null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node rootNode) {
        if (rootNode == null) {
            return 0;
        }
        return 1 + size(rootNode.left) + size(rootNode.right);
    }

    public int height() {
        return height(root);
    }

    private int height(Node rootNode) {
        if (rootNode == null) {
            return 0;
        }
        return 1 + Math.max(height(rootNode.left), height(rootNode.right));
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
        // Insert into an empty tree
        if (root == null) {
            root = new Node(tgt);
            return null;
        }
        // Find parent node
        Node parent = null;
        Node current = root;
        while (current != null) {
            if (tgt.compareTo(current.data) < 0) {
                parent = current;
                current = current.left;
            } else if (tgt.compareTo(current.data) > 0) {
                parent = current;
                current = current.right;
            } else { // tgt == current.data
                parent = current;
                break;
            }
        }

        E old = null;

        // Insert new node
        if (tgt.compareTo(parent.data) < 0) {
            parent.left = new Node(tgt);
        } else if (tgt.compareTo(parent.data) > 0) {
            parent.right = new Node(tgt);
        } else {
            old = parent.data;
        }

        return old;
    }

    public E lookup(E tgt) {
        return lookup(root, tgt);
    }

    private E lookup(Node rootNode, E tgt) {
        // throws exception when not found
        if (root == null) {
            throw new RuntimeException("Tree is empty");
        } else if (tgt.compareTo(rootNode.data) == 0) { // tgt == root
            return rootNode.data;
        } else if (tgt.compareTo(rootNode.data) < 0) { // tgt < root
            return lookup(rootNode.left, tgt);
        } else { // tgt > root
            return lookup(rootNode.right, tgt);
        }
    }

    public boolean contains(E tgt) {
        try {
            lookup(tgt);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    public E remove(E tgt) { // returns old element (or null if not found)
        // Find node to remove
        Node parent = null;
        Node current = root;
        while (current != null) {
            if (tgt.compareTo(current.data) < 0) {
                parent = current;
                current = current.left;
            } else if (tgt.compareTo(current.data) > 0) {
                parent = current;
                current = current.right;
            } else { // tgt == current.data
                break;
            }
        }
        if (current == null) {
            return null;
        }

        E old = current.data;

        if (current.left == null && current.right == null) { // no children, noad is a leaf node
            current = null;
        } else if (current.left == null ^ current.right == null) { // one child
            // Do something
        } else { // two children
            // Do something
        }

        return old;

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