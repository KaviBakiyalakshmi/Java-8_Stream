package Streams_concept;

import java.util.Arrays;
import java.util.List;

public class AverageOfListOfInteger {

	public static void main(String[] args) {
		List<Integer> nums = Arrays.asList(1, 3, 6, 8, 10, 18, 36);

        // Calculate the average using Java streams
        double average = nums.stream()
            .mapToInt(Integer::intValue) 
            .average()                   
            .orElse(0.0);                

        System.out.println("Average: " + average);

	}

}
