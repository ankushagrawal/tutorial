package com.ankush.tutorial.stack;

import java.util.Stack;

public class ReverseWords {

    public static void main(String[] args)
    {
        String exp = "getting good at coding needs a lot of practice";
        reverseWords(exp);
    }

    private static void reverseWords(String exp) {
        String output = "";
        exp = exp+ " ";
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            Character character = exp.charAt(i);
            if (character != ' ') {
                output += character;
            } else {
                stack.push(output);
                output = "";
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop()+ " ");
        }
    }
}
