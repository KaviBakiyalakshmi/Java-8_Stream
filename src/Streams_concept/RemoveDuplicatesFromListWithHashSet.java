package Streams_concept;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicatesFromListWithHashSet {
    public static void main(String[] args) {
        List<Integer> numsToRemove = Arrays.asList(10, 23, 22, 23, 24, 24, 33, 15, 26, 15);

        // Create a HashSet to store unique elements
        Set<Integer> uniqueNumsSet = new HashSet<>(numsToRemove);

        // Create a new list to store unique elements in the order they appear
        List<Integer> uniqueNumsList = new ArrayList<>(uniqueNumsSet);

        System.out.println("Original List: " + numsToRemove);
        System.out.println("List without Duplicates: " + uniqueNumsList);
    }
}
