package cs284;

import java.util.Objects;

public class ChainTest {
    public static void main(String[] args) {
//        ChainedHashMap<String, String> map = new ChainedHashMap<>();
//        System.out.println(map.put("a", "aaa"));
//        System.out.println(map.put("b", "bbb"));
//        System.out.println(map.put("A", "ccc"));
//        System.out.println(map.put("Aa", "Aaa"));
//        System.out.println(map.put("BB", "BBb"));

//        System.out.println();

//        System.out.println(map.get("a"));
//        System.out.println(map.get("b"));
//        System.out.println(map.get("A"));
//        System.out.println(map.get("Aa"));
//        System.out.println(map.get("BB"));

//        System.out.println();

//        System.out.println(map.put("BB", "hahahaha"));
//        System.out.println(map.get("BB"));

//        System.out.println(Objects.hashCode("Aa"));
//        System.out.println(Objects.hashCode("BB"));

//        System.out.println();
//
//        System.out.println(map.remove("BB"));
//
//        System.out.println();
//
//        System.out.println(map.get("BB"));
//
//        System.out.println();

//        System.out.println(map.put("DD", null));
//        System.out.println(map.put(null,"swoosh"));
//        System.out.println(map.get("DD"));
//        System.out.println(map.get(null));

//        System.out.println();
//        System.out.println("Going into for each loop");
//        for (Entry<String, String> entry : map) {
//            System.out.println(entry);
//        }

        ChainedHashMap<String, String> map = new ChainedHashMap<>();
//        for (int i = 0; i < 100; i++) {
//            map.put(i, i % 10);
//            System.out.println(map.get(i));
//        }

//        map.put("this", 1);
//        map.put("coder", 2);
//        map.put("this", 4);
//        map.put("hi", 5);

//        map.put("Aa", 0);
//        map.put("Siblings", 1);
//        map.put("b", 2);
//        map.put("Teheran", 3);
//        map.put("BB", 4);
//        map.put("a", 5);
//        map.put("Aa", 23);

        for (int N = 0; N < 1024; N++) {
            map.put("key" + N, "value" + N);
        }

        System.out.println("In for loop");
        for (Entry<String, String> entry : map) {
            System.out.println(entry);
        }
    }
}
