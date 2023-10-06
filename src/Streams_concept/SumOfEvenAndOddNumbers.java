package Streams_concept;

import java.util.Arrays;
import java.util.List;

public class SumOfEvenAndOddNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

       
        int sumOfEvenNumbers = numbers.stream()
                .filter(num -> num % 2 ==0)
                .mapToInt(Integer::intValue)
                .sum(); // Calculate the sum

        int sumOfOddNumbers = numbers.stream()
                .filter(num -> num % 2 != 0) 
                .mapToInt(Integer::intValue) 
                .sum(); // Calculate the sum

        System.out.println("List of Numbers: " + numbers);
        System.out.println("Sum of Even Numbers: " + sumOfEvenNumbers);
        System.out.println("Sum of Odd Numbers: " + sumOfOddNumbers);
    }
}

