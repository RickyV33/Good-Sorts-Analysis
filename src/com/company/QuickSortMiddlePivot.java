package com.company;

import java.util.Random;

public class QuickSortMiddlePivot extends QuickSort{

    public QuickSortMiddlePivot() {
        super("MiddlePivot");
    }

    protected int[] hoarePartition(int[] numList, int low, int high) {
        int size = high+1 - low;
        int index = (int)Math.ceil(size/2) + low;
        int pivot = numList[index];
        int i = low;
        int j = high + 1;
        int basicOpCount = 0;
        numList[index] = numList[low];
        numList[low] = pivot;

        while (true) {
            do {
                ++basicOpCount;
                ++i;
                if (i > high) {
                    break;
                }
            } while (numList[i] < pivot);
            do {
                ++basicOpCount;
                --j;
            } while (numList[j] > pivot);

            if (i < j) {
                int tempInt = numList[j];
                numList[j] = numList[i];
                numList[i] = tempInt;
            } else {
                int tempInt = numList[j];
                numList[j] = numList[low];
                numList[low] = tempInt;
                int[] results = new int[2];
                results[0] = j;
                results[1] = basicOpCount;
                return results;
            }
        }
    }
}
