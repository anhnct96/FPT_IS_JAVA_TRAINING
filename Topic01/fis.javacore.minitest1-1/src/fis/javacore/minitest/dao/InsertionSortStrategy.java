package fis.javacore.minitest.dao;

public class InsertionSortStrategy implements ISortStrategy {

    @Override
    public void sort(Comparable[] data, int count) {
        for (int i = 1 ; i < data.length ; i++) {
            Comparable key = data[i];
            int j = i - 1;
            while (j >=0 && data[j].compareTo(key) < 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j+1] = key;
        }
    }
}
