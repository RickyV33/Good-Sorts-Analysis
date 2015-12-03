package com.company;

import java.util.Arrays;
import java.util.Random;

public class QuickSortRandomPivot extends QuickSort{

    /**
     * Default constructor
     */
    public QuickSortRandomPivot() {
        super("RandomPivot");
    }

    /**
     * This method partitions numList into one half with all values less than the pivot and the other half greater than
     * or equal to the pivot. The pivot is calculated randomly based on the range of the low index and high index.
     *
     * @param numList The list that is partitioned.
     * @param low The lowest index of the list.
     * @param high The highest index of the list.
     * @return The index that divides the numList into values less than the pivot to the left and greater than to the right.
     */
    protected int[] hoarePartition(int[] numList, int low, int high) {
        Random random = new Random();
        int index = random.nextInt(high+1-low) + low;
        int pivot = numList[index];
        int i = low;
        int j = high + 1;
        int basicOpCount = 0;

        //swap the lowest index with the pivot
        swap(numList, index, low);
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
                //Swap the numbers so the lower number
                // goes to the left and higher goes to
                // the right
                swap(numList, i, j);
            } else {
                int[] results = new int[2];
                //Swap the pivot back into the middle
                // of the left and right side of the list
                swap(numList, j, low);
                results[0] = j;
                results[1] = basicOpCount;
                return results;
            }
        }
    }
}