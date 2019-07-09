package com.ankush.tutorial.greedy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinSwapForBracketBalancing {
    /**
     * You are given a string of 2N characters consisting of N ‘[‘ brackets and N ‘]’ brackets.
     * A string is considered balanced if it can be represented in the for S2[S1]
     * where S1 and S2 are balanced strings. We can make an unbalanced string balanced
     * by swapping adjacent characters. Calculate the minimum number of swaps necessary to make a string balanced.
     *
     * Examples:
     * Input  : []][][
     * Output : 2
     * First swap: Position 3 and 4
     * [][]][
     * Second swap: Position 5 and 6
     * [][][]
     *
     * Input  : [[][]]
     * Output : 0
     * String is already balanced.
     */

    public static int findSwapForBalancingBrackets(String input) {
        List<Integer> indexOfOpeningBrackets = new ArrayList<>();
        //traverse string to find position of opening bracket
        for (int i = 0; i<input.length(); i++) {
            if (input.charAt(i) == '[') {
                indexOfOpeningBrackets.add(i);
            }
        }

        int currCountOfOpenedBracket = 0;
        int totalSwaps = 0;
        int nextPosOfOpeningBracket = 0;
        for (int i = 0; i<input.length(); i++) {
            if (input.charAt(i) == '[') {
                currCountOfOpenedBracket++;
                nextPosOfOpeningBracket++;
            } else {
                currCountOfOpenedBracket--;
            }

            if (currCountOfOpenedBracket < 0) {
                totalSwaps += indexOfOpeningBrackets.get(nextPosOfOpeningBracket) - i;
                int posX = i;
                int posY = indexOfOpeningBrackets.get(nextPosOfOpeningBracket);

                input = swap(posX, posY, input);
                nextPosOfOpeningBracket++;

                currCountOfOpenedBracket =1;
            }
        }
        return totalSwaps;
    }

    private static String swap(int posX, int posY, String input) {
        char[] c = input.toCharArray();
        char c1 = c[posX];
        char c2 = c[posY];
        c[posX] = c2;
        c[posY] = c1;
        return String.valueOf(c);
    }

    public static void main(String[] args) {
        String s = "";
        s = "][";
        System.out.println(findSwapForBalancingBrackets(s) );
        s = "[]][][";
        System.out.println(findSwapForBalancingBrackets(s) );
        s = "[[][]]";
        System.out.println(findSwapForBalancingBrackets(s) );
    }




}
