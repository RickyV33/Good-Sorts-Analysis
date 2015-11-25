package com.company;

public class HeapSort extends Sort{

    public HeapSort() {
        super("HeapSort");
    }

    @Override
    public int[] sort(int[] list) {
        int[] copyList = list.clone();
        int basicOpCount;

        size = list.length;
        Long start = System.nanoTime();
        basicOpCount = heapSort(copyList);
        durationOfSort  += System.nanoTime() - start;
        this.basicOpCount += basicOpCount;
        return copyList;
    }

    //Sorts an array using heap sort
    //Returns the basic operation count
    int heapSort(int[] numbers) {
        int basicOpCount = makeHeap(numbers);

        for (int i = 1; i < numbers.length-1; i++) {
            int newSize = numbers.length-i;
            int temp = numbers[newSize];
            numbers[newSize] = numbers[0];
            numbers[0] = temp;

            if (newSize == 2) {
                basicOpCount++;
                if (numbers[1] < numbers[0]) {
                    temp = numbers[1];
                    numbers[1] = numbers[0];
                    numbers[0] = temp;
                }
            } else
                basicOpCount += heapify(numbers, 0, newSize);
        }
        return basicOpCount;
    }

    int makeHeap(int[] numbers) {
        int basicOpCount = 0;
        for (int i = (int) Math.floor(((double) numbers.length)/2)-1; i >= 0; i--)
            basicOpCount += heapify(numbers, i, numbers.length);
        return basicOpCount;
    }

    int heapify(int[] numbers, int rootPos, int size) {
        int basicOpCount = 0;
        int k = rootPos+1;
        int v = numbers[k-1];
        boolean heap = false;
        while (!heap && 2*k <= size) {
            int j = 2*k;
            if (j < size) {
                basicOpCount++;
                if (numbers[j-1] < numbers[j])
                    j++;
            }
            basicOpCount++;
            if (v >= numbers[j-1])
                heap = true;
            else {
                numbers[k-1] = numbers[j-1];
                k = j;
            }
        }
        numbers[k-1] = v;
        return basicOpCount;
    }
}
