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
        deleteExistingResults(sorts);

        for (int size: sizes) {
            //Generates the integer lists for each size
            generateLists(numListsMap, size);
            for (Map.Entry<String, int[]> entry: numListsMap.entrySet()) {
                for (Sort sort: sorts) {
                    for (int i = 0; i < NUM_OF_TESTS; ++i) {
                        //Sorts the list with each sorting algorithm
                        sort.sort(entry.getValue());
                    }
                }
                writeToFile(entry.getKey(), sorts);
                //Resets the time, size, and basic operation values for each sort
                for (Sort sort: sorts) {
                    sort.reset();
                }
            }
        }
    }

    /**
     * Generates an array of all the list sizes used for the analysis
     * @param list The list that the sizes are stored in
     */
    private void generateListSizes(int[] list) {
        for (int i = 0; i < SIZE; ++i) {
            list[i] = (int) Math.pow(10.0, i+1.0);
        }
    }

    /**
     * Generates a list of all the sorting algorithms used
     * @param sorts A list of all the sorts
     */
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

    /**
     * Generates all the integers lists used by the sorting algorithms
     * @param numListsMap The list that all the lists will be stored in
     * @param size The size of each list
     */
    private void generateLists(Map<String, int[]> numListsMap, int size) {
        IntListGenerator generator = new IntListGenerator();

        //Generates the different types of lists we'll be using
        numListsMap.put("Random List", generator.randomList(size, MILLION));
        numListsMap.put("Ascending List", generator.sortedList(size, MILLION, true));
        numListsMap.put("Descending List", generator.sortedList(size, MILLION, false));
        numListsMap.put("Sorted 25% List", generator.partiallySortedList(size, MILLION, TWENTY_FIVE_PERCENT));
        numListsMap.put("Sorted 50% List", generator.partiallySortedList(size, MILLION, FIFTY_PERCENT));
        numListsMap.put("Sorted 75% List", generator.partiallySortedList(size, MILLION, SEVENTY_FIVE_PERCENT));
        numListsMap.put("Single Integer List", generator.repeatingList(size, STATIC_INT, STATIC_INT));
        numListsMap.put("Alternating Pattern List", generator.repeatingList(size, PATTERN_SIZE, PATTERN_SIZE));
        numListsMap.put("Random Pattern List", generator.randomList(size, PATTERN_SIZE));
    }

    /**
     * Writes the analytical information of the sort into a file based named from the list
     * @param listName The name of the list that is used as the filename
     * @param sorts The list of sorts that are iterated through and written to a file
     */
    private void writeToFile(String listName, List<Sort> sorts) {
        PrintWriter writer;
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
                }
                fs = new FileOutputStream(file, true);
                writer = new PrintWriter(fs);

                writer.println(listName);
                writer.println("Basic Operation count average: " + basicOpAvg);
                writer.println("Time average: " + durationAvg );
                writer.println("Size: " + size);
                writer.println();
                writer.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    private void deleteExistingResults(List<Sort> sorts) {
        File file;

        for (Sort sort: sorts) {
            file = new File("Results/" + sort.getTypeOfSort() + ".txt");
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
