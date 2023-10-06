package Streams_concept;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCaseConversion {
    public static void main(String[] args) {
        List<String> colors = Arrays.asList("RED", "grEEn", "white", "Orange", "pink");

        
        List<String> upperCaseColors = colors.stream()
                .map(String::toUpperCase) 
                .collect(Collectors.toList()); 

        List<String> lowerCaseColors = colors.stream()
                .map(String::toLowerCase) 
                .collect(Collectors.toList()); 

        System.out.println("Original List: " + colors);
        System.out.println("Uppercase List: " + upperCaseColors);
        System.out.println("Lowercase List: " + lowerCaseColors);
    }
}
