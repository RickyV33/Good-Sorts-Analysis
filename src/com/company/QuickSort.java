package com.company;

import java.util.Random;

public class QuickSort extends Sort{

    public QuickSort() {
        super("QuickSort");
    }

    @Override
    public int[] sort(int[] list) {
        int[] copyList = list.clone();
        size = list.length;
        Long start = System.nanoTime();
        basicOpCount = quickSort(copyList, 0, size-1);
        durationOfSort  = System.nanoTime() - start;
        return copyList;
    }

    private int quickSort(int[] numList, int low, int high) {
        int splitIndex;
        int[] results;
        int basicOpCount = 0;

        if (low < high) {
            results = hoarePartition(numList, low, high);
            splitIndex = results[0];
            basicOpCount = results[1];
            basicOpCount += quickSort(numList, low, splitIndex - 1);
            basicOpCount += quickSort(numList, splitIndex + 1, high);
        }

        return basicOpCount;
    }

    private  int[] hoarePartition(int[] numList, int low, int high) {
        Random random = new Random();
        int index = random.nextInt(high+1-low) + low;
        int pivot = numList[index];
        int i = low;
        int j = high + 1;
        int basicOpCount = 0;

        while (true) {
            do {
                ++i;
                if (i > high) {
                    break;
                }
            } while (numList[i] < pivot);
            do {
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
