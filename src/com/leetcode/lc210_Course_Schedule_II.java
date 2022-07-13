package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class lc210_Course_Schedule_II {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Node[] map = new Node[numCourses];

        for (int i = 0; i < numCourses; i++) {
            map[i] = new Node(i);
        }

        for (int[] arr : prerequisites) {
            int from = arr[1];
            int to = arr[0];

            Node fromNode = map[from];
            Node toNode = map[to];

            fromNode.out.add(toNode);
            toNode.in++;
        }

        int count = map.length;
        Queue<Node> zeroQue = new LinkedList<>();

        for (Node node : map) {
            if (node.in == 0)
                zeroQue.add(node);
        }

        List<Integer> list = new ArrayList<>();

        while (!zeroQue.isEmpty()) {
            Node node = zeroQue.poll();

            count--;
            list.add(node.id);
            for (Node next : node.out) {
                next.in--;
                if (next.in == 0) {
                    zeroQue.add(next);
                }
            }
        }

        if (count != 0)
            return new int[0];

        return toArray(list);
    }

    int[] toArray(List<Integer> list) {
        int[] arr = new int[list.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    class Node {
        int id;
        int in;
        List<Node> out;

        Node(int id) {
            this.id = id;
            out = new ArrayList<>();
        }
    }

    @Test
    public void test01() {
        Assertions.assertArrayEquals(new int[]{0,1}, findOrder(2, new int[][]{{1,0}}));
        Assertions.assertArrayEquals(new int[]{0}, findOrder(1, new int[][]{}));
        Assertions.assertArrayEquals(new int[]{0,1}, findOrder(2, new int[][]{}));
    }
}
