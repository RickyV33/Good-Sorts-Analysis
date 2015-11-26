package com.company;

public class MergeSort extends Sort{

    public MergeSort() {
        super("MergeSort");
    }

    @Override
    public int[] sort(int[] list) {
        int[] copyList = list.clone();
        int basicOpCount;

        size = list.length;
        long start = System.nanoTime();
        basicOpCount = mergeSort(copyList);
        durationOfSort  += (System.nanoTime() - start) / 1000000;
        this.basicOpCount += basicOpCount;
        return copyList;
    }

    private int mergeSort(int[] numList) {
        double size = (double) numList.length;
        int firstHalfSize = (int) Math.floor(size/2.0);
        int secondHalfSize = (int) Math.ceil(size / 2.0);
        int[] firstHalf = new int[firstHalfSize];
        int[] secondHalf = new int[secondHalfSize];
        int basicOpCount = 0;

        if (size > 1) {
            System.arraycopy(numList, 0, firstHalf, 0, firstHalfSize);
            System.arraycopy(numList, firstHalfSize, secondHalf, 0, secondHalfSize);
            basicOpCount += mergeSort(firstHalf);
            basicOpCount += mergeSort(secondHalf);
            basicOpCount += merge(firstHalf, secondHalf, numList);
        }
        return basicOpCount;
    }

    private int merge(int[] firstHalf, int[] secondHalf, int[] numList) {
        int firstHalfSize = firstHalf.length;
        int secondHalfSize = secondHalf.length;
        int firstHalfIndex = 0, secondHalfIndex = 0, listIndex = 0;
        int basicOpCount = 0;

        while (firstHalfIndex < firstHalfSize && secondHalfIndex < secondHalfSize) {
            ++basicOpCount;
            if (firstHalf[firstHalfIndex] < secondHalf[secondHalfIndex]) {
                numList[listIndex] = firstHalf[firstHalfIndex];
                ++firstHalfIndex;
            } else {
                numList[listIndex] = secondHalf[secondHalfIndex];
                ++secondHalfIndex;
            }
            ++listIndex;
        }
        if (firstHalfIndex == firstHalfSize) {
            System.arraycopy(secondHalf, secondHalfIndex, numList, listIndex, secondHalfSize - secondHalfIndex);
        } else {
            System.arraycopy(firstHalf, firstHalfIndex, numList, listIndex, firstHalfSize - firstHalfIndex);
        }
        return basicOpCount;

    }

}
