package com.company;

public class HeapSort extends Sort{

    public HeapSort() {
        super("HeapSort");
    }

    @Override
    public int[] sort(int[] list) {
        int[] copyList = list.clone();
        size = list.length;
        Long start = System.nanoTime();
        basicOpCount = heapSort(copyList);
        durationOfSort  = System.nanoTime() - start;
        return copyList;
    }

    //Returns the basic operation count
    private int heapSort(int[] numbers) {
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
            } else {
                int j = 0;
                int k = 0;
                basicOpCount++;  //Or += 1
                if (numbers[j] > numbers[j+k+1])
                    basicOpCount++;
                while (numbers[j] < numbers[j+k+1] || numbers[j] < numbers[j+k+2]) {
                    basicOpCount++;
                    if (numbers[j+k+1] > numbers[j+k+2]) {
                        temp = numbers[j+k+1];
                        numbers[j+k+1] = numbers[j];
                        numbers[j] = temp;
                        j += k+1;
                        k = 2*k+1;
                    } else {
                        temp = numbers[j+k+2];
                        numbers[j+k+2] = numbers[j];
                        numbers[j] = temp;
                        j += k+2;
                        k = 2*k+2;
                    }
                    if (j+k+2 >= newSize) {
                        if (j+k+1 >= newSize)
                            break;
                        basicOpCount++;
                        if (numbers[j+k+1] > numbers[j]) {
                            temp = numbers[j+k+1];
                            numbers[j+k+1] = numbers[j];
                            numbers[j] = temp;
                        }
                        break;
                    }
                    basicOpCount++;
                    if (numbers[j] > numbers[j+k+1])
                        basicOpCount++;
                }
            }
        }
        return basicOpCount;
    }

    //Constructs a heap from input array
    //Returns the basic operation count
    private int makeHeap(int[] numbers) {
        int basicOpCount = 0;
        for (int i = (int) Math.floor(((double) numbers.length)/2); i > 0; i--) {
            int k = i;
            int v = numbers[k-1];
            boolean heap = false;
            while (!heap && 2*k <= numbers.length) {
                int j = 2*k;
                if (j < numbers.length) {
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
        }
        return basicOpCount;
    }
}
