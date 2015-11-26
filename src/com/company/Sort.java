package com.company;

public abstract class Sort {
    private String typeOfSort;
    protected long basicOpCount;
    protected double durationOfSort;
    protected int size;

    public Sort() {}

    public Sort(String typeOfSort){
        this.typeOfSort = typeOfSort;
    }

    public abstract int[] sort(int[] list);

    public void reset() {
        basicOpCount = 0;
        durationOfSort = 0;
        size = 0;
    }

    public String getTypeOfSort() {
        return typeOfSort;
    }

    public long getBasicOpCount() {
        return basicOpCount;
    }

    public double getDurationOfSort() {
        return durationOfSort;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sort: " + typeOfSort);
        sb.append("\nSize of list: " + size);
        sb.append("\nDuration to sort: " + durationOfSort + " nanoseconds");
        sb.append("\nBasic operation count: " + basicOpCount);

        return sb.toString();
    }
}
