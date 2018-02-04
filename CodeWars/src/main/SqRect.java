package main;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SqRect {

    public static List<Integer> sqInRect(int lng, int wdth) {
        List<Integer> subtractedSqSides = null;

        if (!(lng == wdth)) {
            subtractedSqSides = new LinkedList<>();

            int shorterSide = Integer.min(lng, wdth);
            int longerSide = Integer.max(lng, wdth);

            while (shorterSide != 0) {
                subtractedSqSides.addAll(Collections.nCopies(longerSide / shorterSide, shorterSide));

                int remainder = longerSide % shorterSide;
                longerSide = shorterSide;
                shorterSide = remainder;
            }

            subtractedSqSides = Collections.unmodifiableList(subtractedSqSides);
        }

        return subtractedSqSides;
    }

}
