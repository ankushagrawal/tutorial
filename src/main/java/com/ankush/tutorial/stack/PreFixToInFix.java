package com.ankush.tutorial.stack;

import java.util.Stack;

public class PreFixToInFix {

    public static void main(String[] args)
    {
        String exp = "*-A/BC-/AKL";
        System.out.println(toInFix(exp));
    }

    static String toInFix(String exp) {
        String result = new String("");
        Stack<String> stack = new Stack<>();
        for (int i = exp.length()-1 ; i>=0;i--){
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                result = "(" + stack.pop() + c + stack.pop() + ")";
                stack.push(result);
            }
        }

        return result;
    }
}
