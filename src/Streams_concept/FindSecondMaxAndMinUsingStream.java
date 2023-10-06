package Streams_concept;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindSecondMaxAndMinUsingStream {
    public static void main(String[] args) {
        List<Integer> numsToFindSecondMaxMin = Arrays.asList(1, 17, 54, 14, 14, 33, 45, -11);

        // Find the second largest value using stream
        Optional<Integer> secondLargest = numsToFindSecondMaxMin.stream()
                .distinct() // Remove duplicates
                .sorted((a, b) -> b - a) // Sort in descending order
                .skip(1) // Skip the largest value (the first element)
                .findFirst();

        // Find the second smallest value using stream
        Optional<Integer> secondSmallest = numsToFindSecondMaxMin.stream()
                .distinct() // Remove duplicates
                .sorted((a, b) -> a - b) // Sort in ascending order
                .skip(1) // Skip the smallest value (the first element)
                .findFirst();

        if (secondLargest.isPresent() && secondSmallest.isPresent()) {
            System.out.println("Original List: " + numsToFindSecondMaxMin);
            System.out.println("Second Largest Value: " + secondLargest.get());
            System.out.println("Second Smallest Value: " + secondSmallest.get());
        } else {
            System.out.println("The list does not have enough distinct elements.");
        }
    }
}
