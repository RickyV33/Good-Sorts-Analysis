package com.company;

import java.util.Random;

public class IntListGenerator {

    public int[] partiallySortedList(int size, int bound, int percent) {
        Random random = new Random();
        int[] list = new int[size];
        double sizeOfSorted = (percent / 100.0) * size;

        for (int i = 0; i < sizeOfSorted; ++i) {
            list[i] = i;
        }
        for (int i = (int)sizeOfSorted; i < size; ++i) {
           list[i] = random.nextInt(bound);
        }
        return list;
    }

    public int[] sortedList(int size, int bound, boolean ascending) {
        int[] list = new int[size];

        if (ascending) {
            for (int i = 0; i < size; ++i) {
                list[i] = i;
            }
        }
        else {
            for (int i = size-1, k = 0; i > -1; --i, ++k) {
                list[k] = i;
            }
        }

        return list;
    }

    public int[] randomList(int size, int bound) {
        Random random = new Random();
        int[] list = new int[size];

        for (int i = 0; i < size; ++i) {
            list[i] = random.nextInt(bound);
        }
        return list;
    }

    public int[] repeatingList(int size, int bound, int sizeOfPattern) {
        Random random = new Random();
        int[] list = new int[size];
        int[] pattern = new int[sizeOfPattern];

        for (int i = 0; i < sizeOfPattern; ++i) {
            pattern[i] = random.nextInt(bound);
        }

        for (int i = 0; i < size; ++i) {
            list[i] = pattern[i % sizeOfPattern];
        }

        return list;
    }
}
