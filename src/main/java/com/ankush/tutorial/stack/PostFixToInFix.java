package com.ankush.tutorial.stack;

import java.util.Stack;

public class PostFixToInFix {

    public static void main(String[] args)
    {
        String exp = "abcd^e-fgh*+^*i-+";
        System.out.println(toInFix(exp));
    }

    static String toInFix(String exp) {
        String result = new String("");
        Stack<String> stack = new Stack<>();
        for (int i = 0 ; i< exp.length(); i++){
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                String s1 = stack.pop();
                String s2 = stack.pop();
                result = "(" + s2 + c + s1 + ")";
                stack.push(result);
            }
        }
        while(!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}
