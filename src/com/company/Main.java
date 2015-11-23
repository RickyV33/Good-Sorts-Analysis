package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {

        IntListGenerator generator = new IntListGenerator();
        int tenMillion = 10000000;

        int[] randomList = generator.randomList(tenMillion, tenMillion);
        int[] ascendingList = generator.sortedList(tenMillion, tenMillion, true);
        int[] descendingList = generator.sortedList(tenMillion, tenMillion, false);

    }


}
