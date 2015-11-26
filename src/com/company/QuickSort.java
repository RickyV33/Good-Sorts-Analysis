package com.company;

public abstract class QuickSort extends Sort{

    /** Constructor that passes in the type of QuickSort that is being derived from this class. */
    protected QuickSort(String name) {
        super("QuickSort" + name);
    }

    /**
     * This takes in the list and sorts it while also recording the basic operation count and the duration of the sort in
     * milliseconds.
     *
     * @param list The list that is going to be sorted.
     * @return The sorted list.
     */
    @Override
    public int[] sort(int[] list) {
        int[] copyList = list.clone();
        int basicOpCount;

        size = list.length;
        Long start = System.nanoTime();
        basicOpCount = quickSort(copyList, 0, size - 1);
        durationOfSort  += (System.nanoTime() - start) / 1000000;
        this.basicOpCount += basicOpCount;
        return copyList;
    }

    /**
     * This function recursively calls itself with a smaller portion of the list that is divided between the pivot. The
     * split index provided divides the list between the values less than the split index to the left of it, and greater
     * than the pivot index to the right of it after being passed into the hoare partition. This will continue until
     * The only values left are sorted and it sorts itself as the stack unfolds.
     *
     * @param numList The list/sub-lists that is to be sorted.
     * @param low The lowest index of the list.
     * @param high The highest index of the list.
     * @return The basic operation count of the list used for analysis.
     */
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

    /**
     * Abstract method meant to be implemented by different versions of Quick Sort which find different pivots.
     *
     * @param numlist The list to be sorted.
     * @param low The low index of the list.
     * @param high The highest index of the list.
     * @return The index that divides the numList into values less than the pivot to the left and greater than to the right.
     */
    protected abstract int[] hoarePartition(int[] numlist, int low, int high);

    protected void swap(int[] list, int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}