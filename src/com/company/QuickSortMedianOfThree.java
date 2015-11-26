package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class QuickSortMedianOfThree extends QuickSort{

    /**
     * Default constructor
     */
    public QuickSortMedianOfThree() {
        super("MedianOfThree");
    }

    /**
     * This method partitions numList into one half with all values less than the pivot and the other half greater than
     * or equal to the pivot. The pivot is calculated from the median of the first index, middle index, and last index.
     *
     * @param numList The list that is partitioned.
     * @param low The lowest index of the list.
     * @param high The highest index of the list.
     * @return The index that divides the numList into values less than the pivot to the left and greater than to the right.
     */
    protected int[] hoarePartition(int[] numList, int low, int high) {
        int index;
        int size = high - low;
        int middle = (int) Math.ceil(size / 2) + low;
        int basicOpCount = 0;


        if (numList[low] > numList[middle]) {
            if (numList[middle] > numList[high]) {
                index = middle;
            } else if (numList[low] > numList[high]) {
                index = high;
            }
            else {
                index = low;
            }
        } else {
            if (numList[low] > numList[high]) {
                index = low;
            } else if (numList[middle] > numList[high]) {
                index = high;
            }
            else {
                index = middle;
            }
        }


        int pivot = numList[index];
        int i = low;
        int j = high + 1;

        //swap the lowest index with the pivot
        swap(numList, index, low);
        while (true) {
            do {
                ++basicOpCount;
                ++i;
                if (i == high) {
                    break;
                }
            } while (numList[i] < pivot);
            do {
                ++basicOpCount;
                --j;
                if (j == low) {
                    break;
                }
            } while (numList[j] > pivot);

            if (i < j) {
                //Swap the numbers so the lower number goes to the left and higher goes to the right
                swap(numList, i, j);
            } else {
                int[] results = new int[2];
                //Swap the pivot back into the middle of the left and right side of the list
                swap(numList, j, low);
                results[0] = j;
                results[1] = basicOpCount;
                return results;
            }
        }
    }
}
