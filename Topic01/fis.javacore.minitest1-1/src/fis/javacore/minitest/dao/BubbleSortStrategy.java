package fis.javacore.minitest.dao;

public class BubbleSortStrategy implements ISortStrategy {

    @Override
    public void sort(Comparable[] data, int count) {
        Comparable c;
        boolean swapped;
        int i, j;
        for (i = 0 ; i < data.length - 1 ; i++) {
            swapped = false;
            for (j = 0 ; j < data.length - 1 - i; j++) {
                if (data[j].compareTo(data[j+1]) < 0) {
                    c = data[j];
                    data[j] = data[j+1];
                    data[j+1] = c;
                    swapped = true;
                }
            }
            if (swapped == false) {
                break;
            }
        }
    }
}
