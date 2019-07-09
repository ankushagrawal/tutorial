package com.ankush.tutorial.arrays;

public class NegAtEvenPosAtOddMaintainOrder {

    private void arrange(int[] arr) {

        int misOrderIndex = -1;
        if (arr[0] > 0) misOrderIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            if ((i % 2 == 0 && arr[i] > 0) || (i % 2 != 0 && arr[i] < 0)) {
                rotate(arr, misOrderIndex, i);
//                misOrderIndex
            }
        }
    }

    private void rotate(int[] arr, int misOrderIndex, int i) {

    }
}
