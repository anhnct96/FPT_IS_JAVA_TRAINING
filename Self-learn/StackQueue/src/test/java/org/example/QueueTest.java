package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void push() {
        Queue<Integer> queue = new Queue<>();
        queue.push(100);
        queue.push(200);
        queue.push(300);
        queue.push(400);
        queue.push(500);
        queue.push(400);

        assertEquals(200, queue.push(200));

    }

    @Test
    void peek() {
        Queue<Integer> queue = new Queue<>();
        queue.push(100);
        queue.push(200);
        queue.push(300);
        queue.push(400);
        queue.push(500);
        queue.push(400);

        assertEquals(100, queue.peek());
    }

    @Test
    void pop() {
        Queue<Integer> queue = new Queue<>();
        queue.push(100);
        queue.push(200);
        queue.push(300);
        queue.push(400);
        queue.push(500);
        queue.push(400);

        assertEquals(100, queue.pop());
    }

    @Test
    void hasNext() {
        Queue<Integer> queue = new Queue<>();
        queue.push(100);
        queue.push(200);
        queue.push(300);
        queue.push(400);
        queue.push(500);
        queue.push(400);

        while(queue.hasNext()) {
            System.out.println(queue.pop());
        }
    }

    @Test
    void isEmpty() {
        Queue<Integer> queue = new Queue<>();
        queue.push(100);
        queue.push(200);
        queue.push(300);
        queue.push(400);

        Queue<Integer> queue1 = new Queue<>();

        assertEquals(false, queue.isEmpty());
        assertEquals(true, queue1.isEmpty());
    }

    @Test
    void search() throws CloneNotSupportedException {
        Queue<Integer> queue = new Queue<>();
        queue.push(100);
        queue.push(200);
        queue.push(300);
        queue.push(400);
        queue.push(500);
        queue.push(400);

        assertEquals(1,queue.search(100));
        assertEquals(-1,queue.search(600000));
        assertEquals(4,queue.search(400));
    }

    @Test
    void size() {
        Queue<Integer> queue = new Queue<>();
        queue.push(100);
        queue.push(200);
        queue.push(300);
        queue.push(400);
        queue.push(500);
        queue.push(400);

        assertEquals(6,6);
    }
}