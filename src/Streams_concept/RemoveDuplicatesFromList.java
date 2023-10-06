package Streams_concept;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicatesFromList {
    public static void main(String[] args) {
        List<Integer> numsToRemove = Arrays.asList(10, 23, 22, 23, 24, 24, 33, 15, 26, 15);

        
        List<Integer> uniqueNums = numsToRemove.stream()
                .distinct() // Remove duplicates
                .collect(Collectors.toList()); 

        System.out.println("Original List: " + numsToRemove);
        System.out.println("List without Duplicates: " + uniqueNums);
    }
}
