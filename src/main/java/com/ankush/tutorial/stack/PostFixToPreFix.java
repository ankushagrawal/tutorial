package com.ankush.tutorial.stack;

import java.util.Stack;

public class PostFixToPreFix {

    public static void main(String[] args)
    {
        String exp = "ABC/-AK/L-*"; //"*-A/BC-/AKL";
        System.out.println(toPreFix(exp));
    }

    static String toPreFix(String exp) {
        String result = new String("");
        Stack<String> stack = new Stack<>();
        for (int i = 0 ; i<exp.length();i++){
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                String s1 = stack.pop();
                String s2 = stack.pop();
                result = c+ s2+s1 ;
                stack.push(result);
            }
        }

        return result;
    }
}
