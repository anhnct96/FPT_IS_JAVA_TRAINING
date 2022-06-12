package org.example;

import java.util.Stack;

public class Node <T>{
    public T value;
    public Node<T> next;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }
}
class x {
    Stack<Integer> stack = new Stack<>();
}
