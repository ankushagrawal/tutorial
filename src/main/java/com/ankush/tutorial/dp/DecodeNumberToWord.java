package com.ankush.tutorial.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DecodeNumberToWord {
    private static Integer count = 0;

    private static Map<String, Character> encoding = new HashMap<>();

    static {
        encoding.put("1", 'A');
        encoding.put("2", 'B');
        encoding.put("3", 'C');
        encoding.put("4", 'D');
        encoding.put("5", 'E');
        encoding.put("6", 'F');
        encoding.put("7", 'G');
        encoding.put("8", 'H');
        encoding.put("9", 'I');
        encoding.put("10", 'J');
        encoding.put("11", 'K');
        encoding.put("12", 'L');
        encoding.put("13", 'M');
        encoding.put("14", 'N');
        encoding.put("15", 'O');
        encoding.put("16", 'P');
        encoding.put("17", 'Q');
        encoding.put("18", 'R');
        encoding.put("19", 'S');
        encoding.put("20", 'T');
        encoding.put("21", 'U');
        encoding.put("22", 'V');
        encoding.put("23", 'W');
        encoding.put("24", 'X');
        encoding.put("25", 'Y');
        encoding.put("26", 'Z');

    }

    private static Map<String, Integer> ways = new HashMap<>();

    private static void decode(String s, String possibleWord) {

        if (s == null || s.length() == 0) {
//            System.out.println(possibleWord);
            count++;
            return;
        }
        String first =s.substring(0, 1);
        if (encoding.containsKey(first)) {
            if (s.length() > 1) {
                decode(s.substring(1, s.length()), possibleWord + encoding.get(first));
            } else {
                decode(null, possibleWord + encoding.get(first));
            }
        }
        if (s.length() > 1) {
            String two = s.substring(0, 2);
            if (encoding.containsKey(two)) {
                if (s.length() > 2) {
                    decode(s.substring(2, s.length()), possibleWord + encoding.get(two));
                } else {
                    decode(null, possibleWord + encoding.get(two));
                }
            }

        }
    }

    private static int helper(String s, int k) {
        if (k == 0) {
            return 1;
        } else {
            int index = s.length() - k;
            if (s.charAt(index) == '0') {
                return 0;
            }
            if(k >= 2) {
                Integer two = Integer.parseInt(s.substring(index, index+2));
                if (two <= 26) {
                    return helper(s, k-1) + helper(s, k-2);
                } else {
                    return helper(s, k-1);
                }
            } else {
                return helper(s, k-1);
            }
        }

    }

    private static int helper(String s, int k, Integer[] memo) {
        if (k == 0) {
            return 1;
        } else {
            int index = s.length() - k;
            if (s.charAt(index) == '0') {
                return 0;
            }

            if (memo[k] != null) {
                return memo[k];
            }
            int result = helper(s, k-1, memo);
            if(k >= 2) {
                Integer two = Integer.parseInt(s.substring(index, index+2));
                if (two <= 26) {
                    result += helper(s, k-2, memo);
                }
            }
            memo[k] = result;
            return result;
        }

    }


    public static void main(String[] args) {

        String s = "12";
        Long t1 = System.currentTimeMillis();
        decode(s, "");
        Long t2 = System.currentTimeMillis();
        System.out.println("total words possible : " + count);
        System.out.println("time 1 : " + (t2-t1));
        System.out.println("total words possible 2nd way: " + helper(s, s.length()));
        Long t3 = System.currentTimeMillis();
        System.out.println("time 2 : " + (t3-t2));
        System.out.println("total words possible 3rd way: " + helper(s, s.length(), new Integer[s.length()+1]));
        System.out.println("time 3 : " + (System.currentTimeMillis()-t3));
    }
}
