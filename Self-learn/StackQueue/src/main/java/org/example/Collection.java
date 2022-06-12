package org.example;

public interface Collection <T>{
    T push(T value);
    T pop();
    T peek();
    boolean hasNext();
    boolean isEmpty();
    int search(T t) throws CloneNotSupportedException;
    int size();
}
