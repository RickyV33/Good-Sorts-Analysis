package com.company;

import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.io.*;
import java.util.*;

public class Main {
    /** The number of times each sorting algorithm will sort a given list.*/
    public static final int NUM_OF_TESTS = 100;
    /** The size of each list that increments by a multiple of 10 */
    public static final int SIZE = 5;
    /** The range of numbers that can be in the list.*/
    public static final int NUM_RANGE = 9999999;
    /** Static value for 25 percent. */
    public static final int TWENTY_FIVE_PERCENT = 25;
    /** Static value for 50 percent. */
    public static final int FIFTY_PERCENT = 50;
    /** Static value for 75 percent. */
    public static final int SEVENTY_FIVE_PERCENT = 75;
    /** The size of the pattern that is generated as an integer list. */
    public static final int PATTERN_SIZE = 2;
    /** The integer that is used in the list of only one value. */
    public static final int STATIC_INT = 1;

    public static void main(String[] args) throws Exception {
        new Main().run();
    }

    /**
     * The main execution file that iterates through a list of sizes that are used to generate various integer lists
     * that are then passed through different sorting algorithms numerous amounts of times so it can record the average
     * per size per list of each sorting algorithm into a text file.
     */
    public void run() throws Exception {
        Map<String, int[]> numListsMap = new HashMap<String, int[]>();
        List<Sort> sorts = new ArrayList<Sort>();
        int[] sizes = new int[SIZE];

        //Creates a list of the sorts that will be used
        generateSorts(sorts);
        //Generates a list of the sizes used for each list
        generateListSizes(sizes);
        //Deletes the previous .txt files that store the results
        deleteExistingResults(sorts);
        for (int size: sizes) {
            //Generate the pre-assortment of integer lists of length size
            generateLists(numListsMap, size);
            //For each item in the list where the key is the name/type of the list
            //and the value is the list itself.
            for (Map.Entry<String, int[]> list: numListsMap.entrySet()) {
                for (Sort sort: sorts) {
                    //Run the sort on the pseudo random generated
                    // pre-assorted lists NUM_OF_TEST times
                    for (int i = 0; i < NUM_OF_TESTS; ++i) {
                        //Sorts the list
                        int[] sortedList = sort.sort(list.getValue());
                        //Test to see if the list was actually sorted
                        int[] sortedClone = list.getValue().clone();
                        Arrays.sort(sortedClone);
                        if (!Arrays.equals(sortedList, sortedClone)) {
                            throw new Exception("Failure to sort "
                                    + list.getKey() + " for sort "
                                    + sort.getTypeOfSort());
                        }
                    }
                }
                //Write the average results for each sort in a file
                writeToFile(list.getKey(), sorts);
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
            list[i] = (int) Math.pow(10, i+1.0);
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
        //sorts.add(quick);
        //sorts.add(merge);
        //sorts.add(medianOfThree);
        //sorts.add(middlePivot);
    }

    /**
     * Generates all the integers lists used by the sorting algorithms
     * @param numListsMap The list that all the lists will be stored in
     * @param size The size of each list
     */
    private void generateLists(Map<String, int[]> numListsMap, int size) {
        IntListGenerator generator = new IntListGenerator();

        //Generates the different types of lists we'll be using
        numListsMap.put("Random List", generator.randomList(size, NUM_RANGE));
        numListsMap.put("Ascending List", generator.sortedList(size, NUM_RANGE, true));
        numListsMap.put("Descending List", generator.sortedList(size, NUM_RANGE, false));
        numListsMap.put("Sorted 25% List", generator.partiallySortedList(size, NUM_RANGE, TWENTY_FIVE_PERCENT));
        numListsMap.put("Sorted 50% List", generator.partiallySortedList(size, NUM_RANGE, FIFTY_PERCENT));
        numListsMap.put("Sorted 75% List", generator.partiallySortedList(size, NUM_RANGE, SEVENTY_FIVE_PERCENT));
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
            double duration = sort.getDurationOfSort();
            long basicOp = sort.getBasicOpCount();
            long basicOpAvg = basicOp / NUM_OF_TESTS;
            double durationAvg = duration / NUM_OF_TESTS;

            try {
                file = new File("Results/" + typeOfSort + ".txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                fs = new FileOutputStream(file, true);
                writer = new PrintWriter(fs);

                writer.println(listName);
                writer.println("Basic operation count average: " + basicOpAvg);
                writer.println("Time average: " + durationAvg + " ms" );
                writer.println("List size: " + size);
                writer.println();
                writer.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    /**
     * Deletes the result text files if they exist
     * @param sorts The list that it iterates through to check if the sort result files exist
     */
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
