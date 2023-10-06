package Streams_concept;

import java.util.Arrays;
import java.util.List;

public class FindMaxAndMinWithoutStream {
    public static void main(String[] args) {
        List<Integer> numsForMaxAndMin = Arrays.asList(1, 17, 54, 14, 14, 33, 45, -11);

        if (numsForMaxAndMin.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        int max = numsForMaxAndMin.get(0);
        int min = numsForMaxAndMin.get(0); 

        for (int num : numsForMaxAndMin) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }

        System.out.println("Original List: " + numsForMaxAndMin);
        System.out.println("Maximum Value: " + max);
        System.out.println("Minimum Value: " + min);
    }
}
