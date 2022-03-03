package cs284;

// open recursion
// recursive method split across subclasses of a parent class (spread across types)

/**
 * open recursion requires a parent class
 * LL<E>
 * /  \
 * Empty<E>   Cell<E>
 */

import java.util.NoSuchElementException;

public interface LL<E> {
    public boolean isEmpty();

    public int size();

    public E first();

    public LL<E> rest();

    public static void main(String[] args) {
        LL<String> l = new Cell<>("hello", new Cell<>("goodbye", new Empty<>()));
        System.out.println(l.size());
    }
}

class Empty<E> implements LL<E> {
    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public E first() {
        throw new NoSuchElementException();
    }

    @Override
    public LL<E> rest() {
        throw new NoSuchElementException();
    }
}

class Cell<E> implements LL<E> {

    private E first;
    private LL<E> rest;

    public Cell(E first, LL<E> rest) {
        this.first = first;
        this.rest = rest;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        // the open recursion call
        return 1 + rest.size();
    }

    @Override
    public E first() {
        return first;
    }

    @Override
    public LL<E> rest() {
        return rest;
    }
}

class Concat<E> implements LL<E> {
    private LL<E> left;
    private LL<E> right;

    public Concat(LL<E> left, LL<E> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int size() {
        return left.size() + right.size();
    }

    @Override
    public boolean isEmpty() {
        return left.isEmpty() && right.isEmpty();
    }

    @Override
    public E first() {
        if (left.isEmpty()) {
            return right.first();
        }

        return left.first();
    }

    @Override
    public LL<E> rest() {
        if (left.isEmpty()) {
            return right.rest();
        }

        return new Concat<>(left.rest(), right);
    }
}
