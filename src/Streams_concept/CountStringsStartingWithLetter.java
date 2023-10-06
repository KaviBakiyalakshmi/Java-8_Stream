package Streams_concept;

import java.util.Arrays;
import java.util.List;

public class CountStringsStartingWithLetter {
    public static void main(String[] args) {
        List<String> colorsWithStartingLetterR = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");

      
        char startingLetter = 'R';

        
        long count = colorsWithStartingLetterR.stream()
                .filter(s -> s.startsWith(String.valueOf(startingLetter)))
                .count(); // Count the filtered strings

        System.out.println("List of Colors: " + colorsWithStartingLetterR);
        System.out.println("Number of Colors Starting with '" + startingLetter + "': " + count);
    }
}
