/**
 * Name: Tyler Seliber
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

package cs284;

import java.util.LinkedList;
import java.util.List;

public class Permutations {

    public static <E> List<List<E>> allInsertions(E elt, List<E> l) {
        return null;
    }

    public static <E> List<List<E>> allPermutations(List<E> l) {
        return null;
    }

    public static void main(String[] args) {
        List<String> l = new LinkedList<>();
        l.add("a");
        l.add("b");
        l.add("c");

        // test allPermutations()
        for (var perm : allPermutations(l)) {
            StringBuffer sb = new StringBuffer();

            for (var s : perm) {
                sb.append(s);
            }

            System.out.println(sb);
            /*
              Output should be:
                 abc
                 bac
                 bca
                 acb
                 cab
                 cba
             */
        }

        // test allInsertions()
        for (var lIns : allInsertions("!", l)) {
            System.out.println(lIns);
            /*
              Output should be:
               [!, a, b, c]
               [a, !, b, c]
               [a, b, !, c]
               [a, b, c, !]
             */
        }
    }
}
