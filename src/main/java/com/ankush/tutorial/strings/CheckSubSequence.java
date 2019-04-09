package com.ankush.tutorial.strings;

public class CheckSubSequence {

    public static boolean isSubSequence(String s1, String s2){
        int j = 0;
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == s1.charAt(j)) {
                j++;
            }
            if (j == s1.length()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "fgfgadfdbfdsfabfdfsdfcdfsdfsdf";
        System.out.println(isSubSequence(str1, str2));
    }
}
