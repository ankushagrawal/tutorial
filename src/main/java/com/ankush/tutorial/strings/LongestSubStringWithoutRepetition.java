package com.ankush.tutorial.strings;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LongestSubStringWithoutRepetition {

    public static int lengthOfLongestSubstring1(String s) {
        int max = 0;
        int startIndex = 0;
        Map<Character, Integer> charIndex = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(charIndex.containsKey(c)) {
                startIndex = Math.max(startIndex, charIndex.get(c) + 1);

            }
            charIndex.put(c, i);

            max = Math.max(max, i - startIndex +1);
        }
        return max;
    }

    public static int lengthOfLongestSubstring(String s) {
        Queue<Character> q = new LinkedList();
        int max = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (q.contains(c)) {
                int localMax = 0;
                boolean pop = true;
                while (pop && !q.isEmpty()) {

                    if(q.peek().equals(c)){
                       pop = false;
                    }
                    q.poll();
                    localMax++;
                    if (localMax > max) {
                        max = localMax;
                    }

                }
            } else {
                if ((q.size() + 1) > max) {
                    max = q.size() + 1;
                }
            }
            q.add(c);
        }
        return (max > q.size() ? max : q.size());

    }

    public static void main(String[] args) {
        String str1 = "abcabcbb";
        System.out.println(lengthOfLongestSubstring1(str1));
    }
}
