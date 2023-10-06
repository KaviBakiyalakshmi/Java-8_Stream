package Streams_concept;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindMaxAndMinUsingStream {
    public static void main(String[] args) {
        List<Integer> numsForMaxAndMin = Arrays.asList(1, 17, 54, 14, 14, 33, 45, -11);

        
        Optional<Integer> max = numsForMaxAndMin.stream()
                .max(Integer::compare);

       
        Optional<Integer> min = numsForMaxAndMin.stream()
                .min(Integer::compare);

        if (max.isPresent() && min.isPresent()) {
            System.out.println("Original List: " + numsForMaxAndMin);
            System.out.println("Maximum Value: " + max.get());
            System.out.println("Minimum Value: " + min.get());
        } else {
            System.out.println("The list is empty.");
        }
    }
}
