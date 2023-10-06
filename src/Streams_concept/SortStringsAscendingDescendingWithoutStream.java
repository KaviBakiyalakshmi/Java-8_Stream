package Streams_concept;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortStringsAscendingDescendingWithoutStream {
    public static void main(String[] args) {
        List<String> colorsToBeSorted = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");

        
        Collections.sort(colorsToBeSorted);

        System.out.println("List of Colors in Ascending Order:");
        for (String color : colorsToBeSorted) {
            System.out.println(color);
        }

        
        Collections.sort(colorsToBeSorted, Collections.reverseOrder());

        System.out.println("\nList of Colors in Descending Order:");
        for (String color : colorsToBeSorted) {
            System.out.println(color);
        }
    }
}
