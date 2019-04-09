package com.ankush.tutorial.stack;

import java.util.Stack;

public class PreFixToPostFix {
    public static void main(String[] args)
    {
        String exp = "*-A/BC-/AKL";
        System.out.println(toPostFix(exp));
    }

    static String toPostFix(String exp) {
        String result = new String("");
        Stack<String> stack = new Stack<>();
        for (int i = exp.length()-1 ; i>=0;i--){
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                result = stack.pop() + stack.pop() + c;
                stack.push(result);
            }
        }

        return result;
    }
}
