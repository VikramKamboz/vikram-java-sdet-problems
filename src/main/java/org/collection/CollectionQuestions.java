package org.collection;


import java.util.HashSet;
import java.util.TreeSet;

public class CollectionQuestions {
    public static void main(String[] args) {

        findDuplicateIds();
        reverseAndPalindrome();
        reverseAndPalindromeTwoPointer();

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


}