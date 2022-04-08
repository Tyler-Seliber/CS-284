/**
 * Name: Tyler Seliber
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

package cs284;

import java.util.NoSuchElementException;

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

        int children() {
            int children = 0;
            if (left != null) {
                children += 1;
            }
            if (right != null) {
                children += 1;
            }
            return children;
        }

        Node getOnlyChild() {
            if (children() > 1) {
                throw new IllegalStateException("Node has more than one child");
            }
            return left != null ? left : right;
        }

        boolean isLeftChildOf(Node parent) {
            return parent.left == this;
        }
    }

    private Node root;

    public BST() {
        root = null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public boolean isPerfect() {
        // A perfect BST of height h has 2^(h-1) - 1 nodes
        if (size() == (Math.pow(2, height()) - 1)) {
            return isPerfect(root);
        }
        return (size() == 0);
    }

    private boolean isPerfect(Node node) {
        if (node == null) {
            return true;
        }
        if (node.children() == 1) {
            return false;
        }
        return isPerfect(node.left) && isPerfect(node.right);
    }

    public boolean isDegenerate() {
        Node node = root;
        try {
            while (node != null) {
                node = node.getOnlyChild();
            }
            return true;
        } catch (IllegalStateException e) { // thrown if any node has more than one child
            return false;
        }
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

    private E insert(Node node, E tgt) {
        E old = null;
        int compare = tgt.compareTo(node.data);

        if (compare == 0) { // tgt == node.data
            old = node.data;
            node.data = tgt;

        } else if (compare < 0) { // tgt < node.data

            if (node.left == null) { // insert if node.left does not exist
                node.left = new Node(tgt);
            } else { // recurse
                old = insert(node.left, tgt);
            }

        } else { // tgt > node.data

            if (node.right == null) { // insert if node.right does not exist
                node.right = new Node(tgt);
            } else { // recurse
                old = insert(node.right, tgt);
            }
        }
        return old;
    }

    public E lookup(E tgt) {
        if (root == null) {
            throw new NoSuchElementException("Tree is empty");
        }
        return lookup(root, tgt);
    }

    private E lookup(Node node, E tgt) {
        // throws exception when not found
        if (node == null) {
            throw new NoSuchElementException("Element " + tgt + " not found");
        } else if (tgt.compareTo(node.data) == 0) { // tgt == node
            return node.data;
        } else if (tgt.compareTo(node.data) < 0) { // tgt < node
            return lookup(node.left, tgt);
        } else { // tgt > node
            return lookup(node.right, tgt);
        }
    }

    public boolean contains(E tgt) {
        try {
            lookup(tgt);
        } catch (NoSuchElementException e) {
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
        Node toRemoveParent = null;
        Node toRemove;
        // Find the node to remove and its parent
        try {
            toRemoveParent = getParentNode(root, tgt);
            // Determine if tgt is the left or right child of the parent
            if (toRemoveParent.left != null && tgt.compareTo(toRemoveParent.left.data) == 0) {
                toRemove = toRemoveParent.left;
            } else {
                toRemove = toRemoveParent.right;
            }
        } catch (NoSuchElementException e) { // tgt is not in the tree
            return null;
        } catch (RuntimeException e) { // tgt is the root of the tree
            toRemove = root;
        }

        E old = toRemove.data;

        // If removing the root
        if (toRemove == root) {

            // If the root is singleton node
            if (root.children() == 0) {
                root = null;
            } else {
                Node min;
                if (root.left == null) { // root has only right child
                    root = root.right;
                } else if (root.right == null) { // root has only left child
                    root = root.left;
                } else { // root has two children, find the minimum in the right subtree to replace the root
                    min = getMin(root.right);
                    E minValue = min.data;
                    remove(minValue);
                    root.data = minValue;
                }
            }
        }

        // toRemove is a leaf node
        else if (toRemove.children() == 0) {
            if (toRemove.isLeftChildOf(toRemoveParent)) {
                toRemoveParent.left = null;
            } else {
                toRemoveParent.right = null;
            }
        }

        // toRemove has one child
        else if (toRemove.children() == 1) {
            if (toRemove.isLeftChildOf(toRemoveParent)) {
                toRemoveParent.left = toRemove.getOnlyChild();
            } else {
                toRemoveParent.right = toRemove.getOnlyChild();
            }
        }

        // toRemove has two children
        else {
            if (toRemove.isLeftChildOf(toRemoveParent)) {
                toRemoveParent.left = toRemove.left;
                toRemoveParent.left.right = toRemove.right;
            } else {
                toRemoveParent.right = toRemove.right;
                toRemoveParent.right.left = toRemove.left;
            }
        }
        return old;
    }

    private Node getMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    public static BST<Integer> makeDegenerate(int size) {
        BST<Integer> degenerateTree = new BST<>();
        for (int i = 0; i < size; i += 1) {
            degenerateTree.insert(i);
        }
        return degenerateTree;
    }

    public static BST<Integer> makePerfect(int height) {
        // Determine the number of nodes in the tree
        int nodes = (int) ((Math.pow(2, height)) - 1);
        // Create an array of values to insert into the tree
        Integer[] values = new Integer[nodes];
        // Fill the array with values [1, nodes] in sorted order
        for (int i = 0; i < nodes; i += 1) {
            values[i] = i + 1;
        }

        // Sorted arrays represent inorder traversal of the tree
        BST<Integer> perfectTree = new BST<>();
        perfectTree.root = perfectTree.makePerfectHelper(values, 0, nodes - 1);

        return perfectTree;
    }

    private Node makePerfectHelper(E[] values, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node newNode = new Node(values[mid]);
        newNode.left = makePerfectHelper(values, start, mid - 1);
        newNode.right = makePerfectHelper(values, mid + 1, end);

        return newNode;
    }

    private Node getParentNode(Node node, E tgt) {
        if (root == null) {
            throw new NoSuchElementException("Tree is empty");
        }
        if (node == null) {
            throw new NoSuchElementException("Element " + tgt + " not found");
        }
        // Check if tgt is the root of the tree
        if (tgt.compareTo(node.data) == 0) {
            throw new RuntimeException(tgt + " is the root of the tree");
        }
        // Check for tgt on the left
        if (tgt.compareTo(node.data) < 0) {
            // Check if tgt is the left child of the node node
            if (node.left != null && tgt.compareTo(node.left.data) == 0) {
                return node;
            } else {
                return getParentNode(node.left, tgt);
            }

        } else { // Check for tgt on the right
            // Check if tgt is the right child of the node node
            if (node.right != null && tgt.compareTo(node.right.data) == 0) {
                return node;
            } else {
                return getParentNode(node.right, tgt);
            }

        }

    }

}