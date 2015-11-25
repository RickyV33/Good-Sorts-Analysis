package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static final int NUM_OF_TESTS = 100;
    public static final int SIZE = 4;
    public static final int MILLION = 100;
    public static final int TWENTY_FIVE_PERCENT = 25;
    public static final int FIFTY_PERCENT = 50;
    public static final int SEVENTY_FIVE_PERCENT = 75;
    public static final int PATTERN_SIZE = 2;
    public static final int STATIC_INT = 1;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        Map<String, int[]> numListsMap = new HashMap<String, int[]>();
        List<Sort> sorts = new ArrayList<Sort>();
        int[] sizes = new int[SIZE];

        generateSorts(sorts);
        generateListSizes(sizes);

        for (int size: sizes) {
            generateLists(numListsMap, size);
            for (Map.Entry<String, int[]> entry: numListsMap.entrySet()) {
                for (Sort sort: sorts) {
                    for (int i = 0; i < NUM_OF_TESTS; ++i) {
                        //Sorts the lists
                        int[] copy = entry.getValue().clone();
                        Arrays.sort(copy);
                        int[] sorted = sort.sort(entry.getValue());
                        if (!Arrays.equals(copy,sorted) ) {

                            System.out.println("DID NOT SORT CORRECTLY");
                            System.out.println(sort.getTypeOfSort());
                            System.out.println(entry.getKey());
                            System.out.println("SYSTEM---->" + Arrays.toString(copy));
                            System.out.println("SORTED BY OUR ARRAY--->" + Arrays.toString(sorted));
                            return;
                        }
                    }
                }
                writeToFile(entry.getKey(), sorts);
                //Resets the time and basic operation values for each sort
                for (Sort sort: sorts) {
                    sort.reset();
                }
            }

        }
    }

    private void generateListSizes(int[] list) {
        for (int i = 0; i < SIZE; ++i) {
            list[i] = (int) Math.pow(10.0, i+1.0);
        }
    }

    private void generateSorts(List<Sort> sorts) {
        MergeSort merge = new MergeSort();
        QuickSort quick = new QuickSortRandomPivot();
        HeapSort heap = new HeapSort();
        QuickSort medianOfThree = new QuickSortMedianOfThree();
        QuickSort middlePivot = new QuickSortMiddlePivot();

        sorts.add(heap);
        sorts.add(quick);
        sorts.add(merge);
        sorts.add(medianOfThree);
        sorts.add(middlePivot);
    }

    private void writeToFile(String listName, List<Sort> sorts) {
        PrintWriter writer = null;
        File file;
        FileOutputStream fs;


        //median of 3 index
        for (Sort sort: sorts) {
            String typeOfSort = sort.getTypeOfSort();
            int size = sort.getSize();
            long duration = sort.getDurationOfSort();
            int basicOp = sort.getBasicOpCount();
            int basicOpAvg = basicOp / size;
            long durationAvg = duration / size;

            try {
                file = new File("Results/" + typeOfSort + ".txt");
                if (!file.exists()) {
                    file.createNewFile();
                    fs = new FileOutputStream(file);
                } else {
                    fs = new FileOutputStream(file, true);
                }
                writer = new PrintWriter(fs);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            writer.println(listName);
            writer.println("Basic Operation count average: " + basicOpAvg);
            writer.println("Time average: " + durationAvg );
            writer.println("Size: " + size);
            writer.println();
            writer.close();
        }

    }

    private void generateLists(Map<String, int[]> numListsMap, int size) {
        IntListGenerator generator = new IntListGenerator();

        //Generates the different types of lists we'll be using
        int[] randomList = generator.randomList(size, MILLION);
        int[] ascendingList =generator.sortedList(size, MILLION, true);
        int[] descendingList = generator.sortedList(size, MILLION, false);
        int[] sorted25PercentList = generator.partiallySortedList(size, MILLION, TWENTY_FIVE_PERCENT);
        int[] sorted50PercentList = generator.partiallySortedList(size, MILLION, FIFTY_PERCENT);
        int[] sorted75PercentList = generator.partiallySortedList(size, MILLION, SEVENTY_FIVE_PERCENT);
        int[] staticList = generator.repeatingList(size, STATIC_INT, STATIC_INT);
        int[] alternatingPatternList = generator.repeatingList(size, PATTERN_SIZE, PATTERN_SIZE);
        int[] randomPatternList = generator.randomList(size, PATTERN_SIZE);

        numListsMap.put("Random List", randomList);
        numListsMap.put("Ascending List", ascendingList);
        numListsMap.put("Descending List", descendingList);
        numListsMap.put("Sorted 25% List", sorted25PercentList);
        numListsMap.put("Sorted 50% List", sorted50PercentList);
        numListsMap.put("Sorted 75% List", sorted75PercentList);
        numListsMap.put("Static List", staticList);
        numListsMap.put("Alternating Pattern List", alternatingPatternList);
        numListsMap.put("Random Pattern List", randomPatternList);
    }


}
