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
        int[][] lists = {randomList, ascendingList, descendingList};


        for (int[] list: lists) {
            // Counts hold {# of comparisons, # of writes}
            int[] countBubble = {0, 0};
            int[] countInsertion = {0, 0};
            int[] countSelection = {0, 0};
            bubbleSort(list, countBubble);
            insertionSort(list, countInsertion);
            selectionSort(list, countSelection);

            System.out.println("{COMPARE, WRITES}");
            System.out.println("BUBBLE");
            System.out.println(Arrays.toString(countBubble));
            System.out.println("INSERTION");
            System.out.println(Arrays.toString(countInsertion));
            System.out.println("SELECTION");
            System.out.println(Arrays.toString(countSelection));
            System.out.println();
        }
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

}
