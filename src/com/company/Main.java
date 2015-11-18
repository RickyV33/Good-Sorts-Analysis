package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        int[] randomList = genRandomList();
        int[] ascendingList = genAscendingList();
        int[] descendingList = genDescendingList();

        //System.out.println(Arrays.toString(randomList));
        //System.out.println(Arrays.toString(mergeSort(randomList)));
        System.out.println(Arrays.toString(randomList));
        quickSort(randomList, 0, randomList.length - 1);
        System.out.println(Arrays.toString(randomList));
    }

    public int[] genRandomList() {
        Random random = new Random();
        int[] list = new int[5];

        for (int i = 0; i < 5; ++i) {
            list[i] = random.nextInt(10);
        }
        return list;
    }
    public int[] genAscendingList() {
        int[] list = new int[100];

        for (int i = 100; i > 0; --i) {
            list[100-i] = i;
        }
        return list;
    }

    public int[] genDescendingList() {
        int[] list = new int[100];

        for (int i = 0; i < 100; ++i) {
            list[i] = i;
        }
        return list;
    }

    public int mergeSort(int[] numList) {
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

    public int quickSort(int[] numList, int low, int high) {
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

    private int[] hoarePartition(int[] numList, int low, int high) {
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

    //Sorts an array using heap sort
    //Returns the basic operation count
    static int heapSort(int[] numbers) {
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
    static int makeHeap(int[] numbers) {
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
