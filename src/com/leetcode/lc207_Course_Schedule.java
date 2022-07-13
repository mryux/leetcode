package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class lc207_Course_Schedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0)
            return true;

        Map<Integer, Node> map = new HashMap<>();

        for (int[] arr : prerequisites) {
            int from = arr[1];
            int to = arr[0];

            if (!map.containsKey(from))
                map.put(from, new Node(from));
            if (!map.containsKey(to))
                map.put(to, new Node(to));

            Node fromNode = map.get(from);
            Node toNode = map.get(to);

            fromNode.out.add(toNode);
            toNode.in++;
        }

        int count = map.size();
        Queue<Node> zeroQue = new LinkedList<>();

        for (int key : map.keySet()) {
            Node node = map.get(key);

            if (node.in == 0)
                zeroQue.add(node);
        }

        while (!zeroQue.isEmpty()) {
            Node node = zeroQue.poll();

            count--;
            for (Node next : node.out) {
                next.in--;
                if (next.in == 0) {
                    zeroQue.add(next);
                }
            }
        }

        return count == 0;
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
        Assertions.assertTrue(canFinish(5, new int[][]{{1,4},{2,4},{3,1},{3,2}}));
        Assertions.assertTrue(canFinish(2, new int[][]{{1,0}}));
        Assertions.assertFalse(canFinish(2, new int[][]{{1,0}, {0,1}}));
    }
}
