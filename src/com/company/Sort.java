package com.company;

public abstract class Sort {
    private String typeOfSort;
    protected int basicOpCount;
    protected double durationOfSort;
    protected int size;

    public Sort() {}
    public Sort(String typeOfSort){
        this.typeOfSort = typeOfSort;
    }
    public abstract int[] sort(int[] list);
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sort: " + typeOfSort);
        sb.append("\nSize of list: " + size);
        sb.append("\nDuration to sort: " + durationOfSort);
        sb.append("\nBasic operation count: " + basicOpCount);

        return sb.toString();
    }
}
