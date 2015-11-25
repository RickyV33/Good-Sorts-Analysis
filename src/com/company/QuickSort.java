package com.company;

public abstract class QuickSort extends Sort{

    public QuickSort() {
        super("QuickSortMedianOfThree");
    }

    protected QuickSort(String name) {
        super("QuickSort" + name);
    }

    @Override
    public int[] sort(int[] list) {
        int[] copyList = list.clone();
        int basicOpCount;

        size = list.length;
        Long start = System.nanoTime();
        basicOpCount = quickSort(copyList, 0, size - 1);
        durationOfSort  += System.nanoTime() - start;
        this.basicOpCount += basicOpCount;
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

    protected abstract int[] hoarePartition(int[] numlist, int low, int high);
}
