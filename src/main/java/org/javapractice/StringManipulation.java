package org.javapractice;

import java.util.HashMap;
import java.util.Map;

public class StringManipulation {
    public static void main(String[] args) {

        countVowelsConsonants();
        reverseWordsSentence();
        reverseIfRAppearsTwiceWithMap();
        checkRCountAndReverse();
    }

    /*
        P41: Count Vowels and Consonants	Easy  ·  Strings

        Given a string, count the number of vowels and consonants separately. Ignore spaces and special characters.
        Input:  "Hello World"
        Output: Vowels=3, Consonants=7
     */

    private static void countVowelsConsonants() {
        String input = "Hello World";
        int vowelCount = 0;
        int consonantCount = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = Character.toLowerCase(input.charAt(i));
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowelCount++;
            } else if (ch >= 'a' && ch <= 'z') {
                consonantCount++;
            }
        }
        System.out.println("vowelCount : " + vowelCount + " === consonantCount : " + consonantCount);
    }

    /*
        P43: Reverse Words in a Sentence	Easy  ·  Strings

        Reverse the order of words in a sentence. Do not reverse individual characters within words.
        Input:  "Hello World from Java"
        Output: "Java from World Hello"
     */
    private static void reverseWordsSentence() {
        String input = "Hello World from Java";
        String[] inputArr = input.trim().split("\\s+");
        String output = "";
        StringBuilder sb = new StringBuilder();
        for (int i = inputArr.length - 1; i >= 0; i--) {
            output += inputArr[i] + " ";
        }
        System.out.println("string : " + output.trim());

        for (int i = inputArr.length - 1; i >= 0; i--) {
            sb.append(inputArr[i]).append(" ");
        }
        System.out.println("sb : " + sb.toString().trim());

    }

/*    P45: Reverse String if 'r' Appears Exactly Twice   ·  Medium · Strings
    Reverse a string only if the character 'r' appears exactly twice. Otherwise return original.
            Input:  "reverse a string" → count r=2 → "esrever a gnirts"
    Input:  "hello world"     → count r=1 → "hello world"*/

    private static void reverseIfRAppearsTwiceWithMap() {
        String input = "reverse a string";
        String[] arr = input.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            Map<Character, Integer> map = charCount(s);
            if (map.containsKey('r') && map.get('r') == 2) {
                sb.append(s).reverse().append(" ");
            } else {
                sb.append(s).append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    private static Map<Character, Integer> charCount(String input) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (charCount.containsKey(ch)) {
                charCount.put(ch, charCount.get(ch) + 1);
            } else {
                charCount.put(ch, 1);
            }
        }
        return charCount;
    }

    private static void checkRCountAndReverse() {
        String input = "reverse a string";
        String[] arr = input.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for(String s : arr) {
            if(charCount(s, 'r') == 2) {
                result.append(s).reverse().append(" ");
            } else {
                result.append(s).append(" ");
            }
        }
        System.out.println(result.toString().trim());
    }

    private static int charCount(String input, char ch) {
        int charCount =0;
        for(int i=0; i<input.length(); i++) {
            if(input.charAt(i) == ch) {
                charCount++;
            }
        }
        return charCount;
    }

}
