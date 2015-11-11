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
    }

    public int[] genRandomList() {
        Random random = new Random();
        int[] list = new int[100];

        for (int i = 0; i < 100; ++i) {
            list[i] = random.nextInt(1000);
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

    public int[] mergeSort(int[] numList) {

       return null;
    }

    public int[] quickSort(int[] numList) {

        return null;
    }

    public int[] heapSort(int[] numList) {

        return null;
    }

}
