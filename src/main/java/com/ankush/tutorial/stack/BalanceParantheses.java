package com.ankush.tutorial.stack;

import java.util.Stack;

public class BalanceParantheses {

    public static void main(String[] args)
    {
        String exp = "((())(";
        System.out.println(checkBalance(exp));
        System.out.println(isBalanced(exp));
        System.out.println(diff(exp));
    }

    public static int diff(String exp) {
        int count = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            Character character = exp.charAt(i);
            if (character == '(') {
                stack.push(character);
            } else {
                if (stack.isEmpty()) {
                    count++;
                }
                stack.pop();
            }
        }
        if (stack.isEmpty())
            return count;
        else
            return count + stack.size();

//        return false;
    }

    public static boolean checkBalance(String exp) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            Character character = exp.charAt(i);
            if (character == '(') {
                stack.push(character);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        if (stack.isEmpty())
            return true;
        return false;
    }

    public static boolean isBalanced(String exp) {
        Stack<Character> stack = new Stack<>();
        for (int i =0; i <exp.length(); i++) {
            char c = exp.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                while (!stack.isEmpty()) {

                    if (stack.peek() != '(') {
                        stack.pop();
                    } else {
                        stack.pop();
                        break;
                    }

                }

            }

        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
