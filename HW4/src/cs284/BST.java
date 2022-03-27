/**
 * Name: Tyler Seliber
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

package cs284;

public class BST<E extends Comparable<? super E>> {
    // you'll want to define a Node type here
    // ... but you have to design it yourself!

    public BST();

    public int size();

    public int height();

    public isPerfect();

    public isDegenerate();

    public E insert(E); // returns old element

    public E lookup(E); // throws exception when not found

    public boolean contains(E);

    public E remove(E); // returns old element (or null if not found)

    public static BST<Integer> makeDegenerate(int size);

    public static BST<Integer> makePerfect(int height);
}