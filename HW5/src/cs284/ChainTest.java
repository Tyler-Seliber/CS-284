package cs284;

import java.util.Objects;

public class ChainTest {
    public static void main(String[] args) {
        ChainedHashMap<String, String> map = new ChainedHashMap<>();
        System.out.println(map.put("a", "aa"));
        System.out.println(map.put("b", "bb"));
        System.out.println(map.put("A", "cc"));
        System.out.println(map.put("Aa", "Aa"));
        System.out.println(map.put("BB", "BB"));

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
    }
}
