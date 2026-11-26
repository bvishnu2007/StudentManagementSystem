import java.util.*;

public class TreeMap2 {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> results = new TreeMap<>();

        results.put(101, 85);
        results.put(102, 90);
        results.put(103, 75);
        results.put(104, 95);
        results.put(105, 88);

        System.out.println("All Results: " + results);

        results.put(103, 82);
        System.out.println("After Update: " + results);

        System.out.println("Highest Roll Number Entry: " + results.lastEntry());
        System.out.println("Lowest Roll Number Entry: " + results.firstEntry());

        System.out.println("Range 102 to 105: " + results.subMap(102, 105));

        System.out.println("Reverse Roll Number Order: " + results.descendingMap());
    }
}