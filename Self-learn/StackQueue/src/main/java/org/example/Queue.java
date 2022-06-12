package org.example;

import java.util.EmptyStackException;

public class Queue<T> implements Collection<T>, Cloneable{
    private Node<T> front = null;
    private Node<T> behind = null;

    @Override
    public T push(T value) {
        if (behind == null) {
            behind = new Node<>(value, null);
            front = behind;
        } else {
            Node<T> temp = new Node<>(value, null);
            behind.next = temp;
            behind = temp;
        }
        return value;
    }

    @Override
    public T peek() {
        return front.value;
    }

    @Override
    public T pop() {
        T top = peek();
        front = front.next;
        if (front == null) {
            behind = null;
        }
        return top;
    }

    @Override
    public boolean hasNext() {
        return front!= null;
    }

    @Override
    public boolean isEmpty() {
        return this.front == this.behind ? true : false;
    }

    @Override
    public int search(T t) throws CloneNotSupportedException {
        Queue queueClone = (Queue) this.clone();
        int i = 0;
        int position = -1;
        while (queueClone.hasNext()) {
            i++;
            if (queueClone.pop().equals(t)) {
                position = i;
                break;
            }
        }
        return position;
    }

    @Override
    public int size() {
        int i = 0;
        while (this.hasNext()) {
            i++;
        }
        return i;
    }
}
