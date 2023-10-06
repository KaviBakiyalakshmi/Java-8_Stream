package Streams_concept;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortStringsAscendingDescending {
    public static void main(String[] args) {
        List<String> colorsToBeSorted = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");

        
        List<String> ascendingOrder = colorsToBeSorted.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        
        List<String> descendingOrder = colorsToBeSorted.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("Original List: " + colorsToBeSorted);
        System.out.println("Sorted in Ascending Order: " + ascendingOrder);
        System.out.println("Sorted in Descending Order: " + descendingOrder);
    }
}
