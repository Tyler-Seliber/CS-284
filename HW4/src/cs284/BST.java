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

        Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    static class Pair<F, S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
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
        // Insert into a non-empty tree
        return insert(root, tgt);
    }

    private E insert(Node current, E tgt) {
        E old = null;
        int compare = tgt.compareTo(current.data);
        if (compare == 0) {
            old = current.data;
            current.data = tgt;
        } else if (compare < 0) { // tgt < current.data
            if (current.left == null) { // insert if current.left does not exist
                current.left = new Node(tgt);
            } else { // recurse
                old = insert(current.left, tgt);
            }
        } else { // tgt > current.data
            if (current.right == null) { // insert if current.right does not exist
                current.right = new Node(tgt);
            } else { // recurse
                old = insert(current.right, tgt);
            }
        }
        return old;
    }

    public E lookup(E tgt) {
        return lookup(root, tgt);
    }

    private E lookup(Node current, E tgt) {
        // throws exception when not found
        if (root == null) {
            throw new RuntimeException("Tree is empty");
        } else if (tgt.compareTo(current.data) == 0) { // tgt == current
            return current.data;
        } else if (tgt.compareTo(current.data) < 0) { // tgt < current
            return lookup(current.left, tgt);
        } else { // tgt > current
            return lookup(current.right, tgt);
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
        // Remove from an empty tree
        if (root == null) {
            return null;
        }
        // Remove from a non-empty tree
        return remove(root, tgt).second;
    }

    private Pair<Node, E> remove(Node current, E tgt) {
//        // tgt not in tree
//        if (current == null) {
//            return new Pair<>(null, null);
//        }
//
//        Node parent = current;
//        Node child = null;
//        E old = null;
//        int compare = tgt.compareTo(parent.data);
//        if (compare < 0) { // tgt < current
//            child = parent.left;
//            parent.left = remove(child, tgt).first;
//        } else if (compare > 0) { // tgt > current
//            child = current.right;
//            parent.right = remove(child, tgt).first;
//        }
//
//        if (child == null) { // tgt == current
//
//        }
//
//        return new Pair(null, null);
        // tgt not in tree
        if (current == null) {
            return null;
        }

//        int compare = tgt.compareTo(current.data);
//        if (parent == null) {
//            if (compare )
//        }
//
//        Node parent = current;
//        if (compare < 0) { // tgt < current
//            current = current.left;
//        } else if (compare > 0) { // tgt > current
//            current = current.right;
//        }


        // --------------------------------------------------

        E old = null;
        Node child = null;
        int compare = tgt.compareTo(current.data);

        if (compare == 0) { // tgt == current
            old = current.data;
            if (current.left == null) {
                E data;
                if (current.right == null) {
                    data = null;
                } else {
                    data = current.right.data;
                }
                current = new Node(data, null, current.right);
            } else if (current.right == null) {
                current = new Node(current.left.data, current.left, null);
            } else { // two children
                current = removeMin(current.right).first;
            }
        } else { // tgt != current
            if (compare < 0) { // tgt < current.data
//                child = remove(current.left, tgt).first;
                current = new Node(current.data, remove(current.left, tgt).first, current.right);
            } else { // tgt > current.data
//                child = remove(current.right, tgt).first;
                current = new Node(current.data, current.left, remove(current.right, tgt).first);
            }
        }
        return new Pair<>(current, old);
    }

    private Pair<Node, E> removeMin(Node current) {
        // tgt not in tree
        if (current == null) {
            return null;
        }

        E old = null;
        if (current.left == null) {
            old = current.data;
            current = current.right;
        } else {
            removeMin(current.left);
        }

        return new Pair<>(current, old);
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