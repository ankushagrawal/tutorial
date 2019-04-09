package com.ankush.tutorial.arrays;


public class Separate1And0 {

    public static void main(String[] args) {
       int[] arr = new int[]{1,0,1,0,1,0,1,1,1};
       display(arr);
       move(arr);
       display(arr);
    }

    private static void move(int[] arr) {
        int count = 0;
        for (int i = 0; i< arr.length; i++) {
            if (arr[i] != 0) {
                arr[count++] = arr[i];
            }
        }
        while(count < arr.length) {
            arr[count++] = 0;
        }
    }

    private static void display(int[] arr) {
        for (int i =0; i < arr.length ; i++) {
            System.out.print(" "+arr[i]);
        }
        System.out.println();
    }

    private static void segregate(int[] arr) {
        int st = 0;
        int end = arr.length-1;
        while (end > st) {
            if (arr[st] == 1) {
                if (arr[end] == 0) {
                    arr[st] = 0;
                    arr[end] = 1;
                    st++;
                    end--;
                } else {
                    end--;
                }
            } else {
                st++;
            }
        }
    }
}
