package main;

public class CharDuplicateFinder {

    public static void main(String[] args) {
        String string1 = "abcde";
        String string2 = "aabbcde";
        String string3 = "aabBcde";
        String string4 = "Indivisibilities";
        String string5 = "aA11";
        String string6 = "ABBA";
        String string7 = "wxdunrgqiuqzhozpu2392875298734205495464107wyxnpxueyQENCUXQwzExqounxwerarx";

        System.out.println("Result " + CountingDuplicates.duplicateCount(string1));
        System.out.println("Result " + CountingDuplicates.duplicateCount(string2));
        System.out.println("Result " + CountingDuplicates.duplicateCount(string3));
        System.out.println("Result " + CountingDuplicates.duplicateCount(string4));
        System.out.println("Result " + CountingDuplicates.duplicateCount(string5));
        System.out.println("Result " + CountingDuplicates.duplicateCount(string6));
        System.out.println("Result " + CountingDuplicates.duplicateCount(string7));

        //0, 2, 2, 2, 2, 2
    }


}
