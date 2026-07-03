package org.javapractice;

public class StringManipulation {
    public static void main(String[] args) {

        countVowelsConsonants();
        reverseWordsSentence();
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
        for(int i =0; i<input.length(); i++) {
            char ch = Character.toLowerCase(input.charAt(i));
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowelCount++;
            } else if(ch>='a' && ch <= 'z') {
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
        for(int i=inputArr.length-1 ; i>=0; i--) {
            output += inputArr[i] + " ";
        }
        System.out.println("string : " +  output.trim());

        for(int i=inputArr.length-1; i>=0; i--) {
            sb.append(inputArr[i]).append(" ");
        }
        System.out.println("sb : " + sb.toString().trim());

    }
}
