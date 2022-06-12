package org.example;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class Stack<T> implements Collection<T>, Cloneable{
    private Node<T> top = null;
    // Node<T> next = null => day la top
    @Override
    public T push(T value) {
        top = new Node<>(value, top);
        return value;
    }

    @Override
    public T peek() {
        if(this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.top.value;
    }

    // rearTop (valueRearTop, top <=> this.top.next) ; top (valueTop, null)
    // xoa top -> rearTop = top
    // this.top = this.top.next
    // tra ve gia tri top bi xoa <=> this.top.value
    @Override
    public T pop() {
        T t = peek();
        this.top = this.top.next;
        return t;
    }

    @Override
    public boolean hasNext() {
        return top != null;
    }

    @Override
    public boolean isEmpty() {
        return this.top == null ? true : false;
    }

    @Override
    public int search(T t) throws CloneNotSupportedException {
        Stack stackClone = (Stack) this.clone();
        int i = 0;
        int position = -1;
        while (stackClone.hasNext()) {
            i++;
            if (stackClone.pop().equals(t)) {
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
