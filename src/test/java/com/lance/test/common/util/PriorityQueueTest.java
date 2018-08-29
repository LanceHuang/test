package com.lance.test.common.util;

import org.junit.Test;

import java.util.PriorityQueue;

public class PriorityQueueTest {

    @Test
    public void test() {
        //It use Comparable or Comparator
        PriorityQueue<String> aQueue = new PriorityQueue<>();
        aQueue.add("5");
        aQueue.add("1");
        aQueue.add("2");
        aQueue.add("4");
        aQueue.add("0");
//        for (String data: aQueue) {
//            System.out.println(data);
//        }
//        System.out.println(aQueue);
        //[0, 1, 2, 5, 4]

        while (!aQueue.isEmpty()) {
            System.out.println(aQueue.poll());
        }
        //0 1 2 3 4 5
    }

    @Test
    public void testErr() {
        PriorityQueue<Dog> aQueue = new PriorityQueue<>();
        aQueue.add(new Dog());
        aQueue.add(new Dog());
        //java.lang.ClassCastException: com.lance.test.common.util.Dog cannot be cast to java.lang.Comparable
    }
}


class Dog {
}