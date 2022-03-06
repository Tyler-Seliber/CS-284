/**
 * Name: Tyler Seliber
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

package cs284;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutations {

    public static <E> List<List<E>> allInsertions(E elt, List<E> l) {

        // Create a new list to store all possible insertions, size is preallocated for efficiency
        List<List<E>> result = new ArrayList<List<E>>(l.size() + 1);

        // Add each possible insertion to the result list
        for (int i = 0; i <= l.size(); i++) {
            // Create a new list to store the current insertion, size is preallocated for efficiency
            List<E> inserted = new ArrayList<>(l.size() + 1);

            // Insert the elements before where elt should be inserted
            inserted.addAll(l.subList(0, i));
            // Insert elt
            inserted.add(elt);
            // Insert the elements after where elt should be inserted
            inserted.addAll(l.subList(i, l.size()));

            // Add the insertion to the list of results
            result.add(inserted);
        }

        return result;
    }

    public static <E> List<List<E>> allPermutations(List<E> l) {
        // Create a new list to store all possible permutations
        List<List<E>> result = new ArrayList<List<E>>();

        if (l.isEmpty() || l.size() == 1) {
            result.add(l);
            return result;
        }

        if (l.size() == 2) {
            return allInsertions(l.get(0), l.subList(1, 2));
        }

        // Split the list into two parts
        E first = l.get(0);
        List<E> rest = l.subList(1, l.size());

        for (var perm : allPermutations(rest)) {
            result.addAll(allInsertions(first, perm));
        }

        return result;
    }


    public static void main(String[] args) {
        List<String> l = new LinkedList<>();
        l.add("a");
        l.add("b");
        l.add("c");

        // Test allPermutations()
        System.out.println("Testing: allPermutations(l):");

        for (var perm : allPermutations(l)) {
            StringBuffer sb = new StringBuffer();

            for (var s : perm) {
                sb.append(s);
            }

            System.out.println(sb);
        }
        /*
          Output should be:
             abc
             bac
             bca
             acb
             cab
             cba
         */

        // Test allInsertions()
        System.out.println("\nTesting: allInsertions(\"!\", l):");

        for (var lIns : allInsertions("!", l)) {
            System.out.println(lIns);
        }
        /*
          Output should be:
           [!, a, b, c]
           [a, !, b, c]
           [a, b, !, c]
           [a, b, c, !]
         */
    }
}
