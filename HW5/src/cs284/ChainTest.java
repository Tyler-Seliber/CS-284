package cs284;

import java.util.Objects;

public class ChainTest {
    public static void main(String[] args) {
        ChainedHashMap<String, String> map = new ChainedHashMap<>();
        System.out.println(map.put("a", "aaa"));
        System.out.println(map.put("b", "bbb"));
        System.out.println(map.put("A", "ccc"));
        System.out.println(map.put("Aa", "Aaa"));
        System.out.println(map.put("BB", "BBb"));

        System.out.println();

        System.out.println(map.get("a"));
        System.out.println(map.get("b"));
        System.out.println(map.get("A"));
        System.out.println(map.get("Aa"));
        System.out.println(map.get("BB"));

        System.out.println();

        System.out.println(map.put("BB", "hahahaha"));
        System.out.println(map.get("BB"));

//        System.out.println(Objects.hashCode("Aa"));
//        System.out.println(Objects.hashCode("BB"));

        System.out.println();

        System.out.println(map.remove("BB"));

        System.out.println();

        System.out.println(map.get("BB"));

        System.out.println();

        System.out.println(map.put("DD", null));
//        System.out.println(map.put(null,"swoosh"));
        System.out.println(map.get("DD"));
//        System.out.println(map.get(null));

        System.out.println();
        System.out.println("Going into for each loop");
        for (Entry<String, String> entry : map) {
            System.out.println(entry);
        }


    }
}
