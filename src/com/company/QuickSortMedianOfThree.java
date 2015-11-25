package com.company;

import java.util.Random;

public class QuickSortMedianOfThree extends QuickSort{

    public QuickSortMedianOfThree() {
        super("MedianOfThree");
    }

    protected int[] hoarePartition(int[] numList, int low, int high) {
        int index;
        int size = (high + 1) - low;
        int middle = (int) Math.ceil(size / 2) + low;
        int basicOpCount = 0;


        //Select median
        basicOpCount++;
        if (numList[low] < numList[middle]) {
            basicOpCount++;
            if (numList[high] < numList[low])
                index = low;
            else {
                basicOpCount++;
                if (numList[middle] < numList[high])
                    index = middle;
                else
                    index = high;
            }
        } else { //middle < x
            basicOpCount++;
            if (numList[high] < numList[middle])
                index = middle;
            else { //middle < z
                basicOpCount++;
                if (numList[low] < numList[high])
                    index = low;
                else
                    index = high;
            }
        }

        int pivot = numList[index];
        int i = low;
        int j = high + 1;
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
