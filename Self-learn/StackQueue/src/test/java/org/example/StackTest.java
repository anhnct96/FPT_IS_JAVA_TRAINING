package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StackTest {

    @Test
    void push() {
        Stack<Integer> stack = new Stack<>();
        stack.push(100);
        stack.push(200);

        assertEquals(300,stack.push(300));
    }

    @Test
    void peek() {
        Stack<Integer> stack = new Stack<>();
        stack.push(100);
        stack.push(200);
        stack.push(300);

        assertEquals(300,stack.peek());
    }

    @Test
    void pop() {
        Stack<Integer> stack = new Stack<>();
        stack.push(100);
        stack.push(200);
        stack.push(300);

        assertEquals(300,stack.pop());
    }

    @Test
    void hasNext() {
        Stack<Integer> stack = new Stack<>();
        stack.push(100);
        stack.push(200);
        stack.push(300);

        while(stack.hasNext()) {
            System.out.println(stack.pop());
        }

    }

    @Test
    void isEmpty() {
        Stack<Integer> stack = new Stack<>();
        stack.push(100);
        stack.push(200);
        stack.push(300);

        Stack<Integer> stack1 = new Stack<>();

        assertEquals(false,stack.isEmpty());
        assertEquals(true,stack1.isEmpty());
    }

    @Test
    void search() throws CloneNotSupportedException {
        Stack<Integer> stack = new Stack<>();
        stack.push(100);
        stack.push(200);
        stack.push(300);
        stack.push(400);
        stack.push(500);
        stack.push(400);

        assertEquals(4,stack.search(300));
        assertEquals(-1,stack.search(600));
        assertEquals(1,stack.search(400));
    }

    @Test
    void size() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3,3);
        stack.pop();
        stack.pop();
        assertEquals(1,1);
    }
}