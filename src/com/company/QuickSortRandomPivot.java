package com.company;

import java.util.Arrays;
import java.util.Random;

public class QuickSortRandomPivot extends QuickSort{

    public QuickSortRandomPivot() {
        super("RandomPivot");
    }

    protected int[] hoarePartition(int[] numList, int low, int high) {
        Random random = new Random();
        int index = random.nextInt(high+1-low) + low;
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
