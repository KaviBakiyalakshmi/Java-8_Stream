package Streams_concept;

import java.util.Arrays;
import java.util.List;

public class CountStringsStartingWithLetterWithoutStream {
    public static void main(String[] args) {
        List<String> colorsWithStartingLetterB = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");

        
        char startingLetter = 'B';

       
        int count = 0;

        
        for (String color : colorsWithStartingLetterB) {
            if (color.startsWith(String.valueOf(startingLetter))) {
                count++;
            }
        }

        System.out.println("List of Colors: " + colorsWithStartingLetterB);
        System.out.println("Number of Colors Starting with '" + startingLetter + "': " + count);
    }
}

