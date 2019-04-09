package com.ankush.tutorial.strings;

import java.util.HashMap;
import java.util.Map;

public class SortStringBasedOnOrderString {

    public static String sort(String str, String pattern) {
        char[] chars = new char[pattern.length()];
        for (int i = 0; i< pattern.length(); i++) {
            chars[i] = pattern.charAt(i);
        }

        Map<Character, Integer> strCount = new HashMap<>();

        for (int i = 0; i< str.length(); i++) {
            Character character = str.charAt(i);
            if (strCount.containsKey(character)) {
                strCount.put(character, strCount.get(character)+1);
            } else {
                strCount.put(character, 1);
            }
        }

        String result = new String("");

        for (int i = 0; i< pattern.length(); i++) {
            if (strCount.containsKey(chars[i])) {
                for (int j = 0; j< strCount.get(chars[i]); j++) {
                    result += chars[i];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String patt = "bxyzca";
        String str = "abcabcabc";
        System.out.println(sort(str, patt));
    }
}
