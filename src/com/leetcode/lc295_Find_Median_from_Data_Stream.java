package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

public class lc295_Find_Median_from_Data_Stream {

    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public lc295_Find_Median_from_Data_Stream() {
        maxHeap = new PriorityQueue<>(10, (a,b)-> {
            return b-a;
        });
        minHeap = new PriorityQueue<>(10);
    }

    public void addNum(int num) {
        if (maxHeap.size() == 0 || maxHeap.peek() >= num)
            maxHeap.add(num);
        else
            minHeap.add(num);

        if (maxHeap.size() - minHeap.size() > 1)
            minHeap.add(maxHeap.poll());
        else if (minHeap.size() - maxHeap.size() > 1)
            maxHeap.add(minHeap.poll());
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size())
            return (maxHeap.peek() + minHeap.peek())/2.0d;

        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
    }

    @Test
    public void test01() {
        addNum(1);
        addNum(2);
        Assertions.assertEquals(1.5d, findMedian());
        addNum(3);
        Assertions.assertEquals(2.0d, findMedian());
    }
}
