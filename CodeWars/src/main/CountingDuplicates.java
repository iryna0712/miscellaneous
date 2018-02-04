package main;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

//make forked
public class CountingDuplicates {

    public static int duplicateCount(String text) {
        Map<Character, Integer> map = new HashMap<>();
        LongAdder counter = new LongAdder();

        for (char character : text.toCharArray()) {
            map.merge(Character.toLowerCase(character), 1, Integer::sum);
        }

        map.forEach((Charact, Num) -> {if (Num > 1) counter.increment();});

        return counter.intValue();
    }
}
