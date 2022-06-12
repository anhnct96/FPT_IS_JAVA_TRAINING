package fis.javacore.minitest.dao;

public class SelectionSortStrategy implements ISortStrategy {

    @Override
    public void sort(Comparable[] data, int count) {
        for (int i = 0 ; i < data.length-1 ; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j].compareTo(data[minIndex]) > 0) {
                    minIndex = j;
                }
            }
            Comparable c = data[minIndex];
            data[minIndex] = data[i];
            data[i] = c;
        }
    }
}