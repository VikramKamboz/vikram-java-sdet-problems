package org.javapractice;

import java.util.*;

public class CollectionQuestions {
    public static void main(String[] args) {

        findDuplicateIds();
        reverseAndPalindrome();
        reverseAndPalindromeTwoPointer();
        characterFrequencyPointer();
        characterFrequencyPointerUpdated();
        highestKeyValuesCount();

    }

    //P1: Find Duplicate Test Case IDs
    private static void findDuplicateIds() {
        String[] input = {"TC001", "TC002", "TC001", "TC003", "TC002", "TC004"};
        TreeSet<String> duplicates = new TreeSet<>();
        TreeSet<String> seen = new TreeSet<>();
        for (String s : input) {
            if (seen.contains(s)) {
                duplicates.add(s);
            } else {
                seen.add(s);
            }
        }
        System.out.println("duplicates : " + duplicates);
        System.out.println("seen : " + seen);
    }


    //P2: Reverse a String and Check Palindrome
    private static void reverseAndPalindrome() {
        String inputString = "racecar";
        boolean isPalindrome = false;
        StringBuilder sb = new StringBuilder();
        for (int i = inputString.length() - 1; i >= 0; i--) {
            sb.append(inputString.charAt(i));
        }
        if (sb.toString().equalsIgnoreCase(inputString)) {
            isPalindrome = true;
        }
        System.out.println("isPalindrome : " + isPalindrome);
    }

    //P2: Reverse a String and Check Palindrome
    private static void reverseAndPalindromeTwoPointer() {
        String inputString = "racecar";
        int start = 0;
        int end = inputString.length() - 1;
        while (start < end) {
            if (inputString.charAt(start) != inputString.charAt(end)) {
                System.out.println("is not palindrome : " + inputString);
                return;
            }
            start++;
            end--;
        }
        System.out.println("is palindrome : " + inputString);
    }

/*    P3: Character Frequency Counter
    Given a string, return a Map with the count of each character. Maintain insertion order.
            Input:  "apple"
    Output: {a=1, p=2, l=1, e=1}*/

    private static void characterFrequencyPointer() {        String input = "apple";
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            if (map.containsKey(input.charAt(i))) {
                map.put(input.charAt(i), map.get(input.charAt(i)) + 1);
            } else {
                map.put(input.charAt(i), 1);
            }
        }
        System.out.println(map);
    }

    private static void characterFrequencyPointerUpdated() {
        String input = "apple";
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }
        System.out.println("characterFrequencyPointerUpdated" + map);
    }

    /*
    P4: Find Key with Highest Value in Map
    Easy  ·  Collections

    Given a Map<String, Integer> of test modules and their failure counts, find the module with the most failures.
    Input:  {Login=5, Search=2, Checkout=8, Profile=
     */

    private static void highestKeyValuesCount() {
        Map<String, Integer> inputMap = new HashMap<>();
        inputMap.put("one", 1);
        inputMap.put("nineteen", 19);
        inputMap.put("three", 3);
        inputMap.put("eight", 8);
        inputMap.put("fifteen", 15);

        String highestValue = inputMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        String highestValueIfNull = inputMap.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
        System.out.println("highestValue : " + highestValue);

    }


}