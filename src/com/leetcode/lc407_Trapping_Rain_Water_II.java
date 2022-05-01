package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

public class lc407_Trapping_Rain_Water_II {
    public int trapRainWater(int[][] heightMap) {
        PriorityQueue<Node> que = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
        boolean[][] visited = new boolean[heightMap.length][heightMap[0].length];
        int[] val = new int[1];

        // add bounds to que
        // first/last row
        for (int i = 0; i < heightMap[0].length; i++) {
            addToQueue(que, 0, i, 0, heightMap, visited, val);
            addToQueue(que, heightMap.length-1, i, 0, heightMap, visited, val);
        }
        // first/last column
        for (int i = 1; i < heightMap.length-1; i++) {
            addToQueue(que, i, 0, 0, heightMap, visited, val);
            addToQueue(que, i, heightMap[0].length-1, 0, heightMap, visited, val);
        }

        int ans = 0;
        int max = Integer.MIN_VALUE;

        while (!que.isEmpty()) {
            Node node = que.poll();

            max = Math.max(max, node.val);
            // add adjacent cells into que
            if (addToQueue(que, node.row-1, node.col, max, heightMap, visited, val)) {
                ans += Math.max(0, max - val[0]);
            }
            if (addToQueue(que, node.row+1, node.col, max, heightMap, visited, val)) {
                ans += Math.max(0, max - val[0]);
            }
            if (addToQueue(que, node.row, node.col-1, max, heightMap, visited, val)) {
                ans += Math.max(0, max - val[0]);
            }
            if (addToQueue(que, node.row, node.col+1, max, heightMap, visited, val)) {
                ans += Math.max(0, max - val[0]);
            }
        }

        return ans;
    }

    private boolean addToQueue(PriorityQueue<Node> que, int row, int col, int max, int[][] map, boolean[][] visited, int[] val) {
        if (row < 0 || row > map.length-1 || col < 0 || col > map[0].length-1)
            return false;

        if (visited[row][col])
            return false;

        val[0] = map[row][col];
        que.add(new Node(Math.max(max, map[row][col]), row, col));
        visited[row][col] = true;
        return true;
    }

    private class Node {
        public int val;
        public int row;
        public int col;

        public Node(int v, int r, int c) {
            val = v;
            row = r;
            col = c;
        }
    }

    @Test
    public void test01() {
        lc407_Trapping_Rain_Water_II solu = new lc407_Trapping_Rain_Water_II();

        Assertions.assertEquals(36, solu.trapRainWater(new int[][] {
                {13,16,15,18,15,15},
                {14,1,8,9,7,9},
                {19,5,4,2,5,10},
                {13,1,7,9,10,3},
                {17,7,5,10,6,1},
                {15,9,8,2,8,3}
        }));

        Assertions.assertEquals(4, solu.trapRainWater(new int[][] {
                {1,4,3,1,3,2},
                {3,2,1,3,2,4},
                {2,3,3,2,3,1}
        }));

        Assertions.assertEquals(10, solu.trapRainWater(new int[][] {
                {3,3,3,3,3},
                {3,2,2,2,3},
                {3,2,1,2,3},
                {3,2,2,2,3},
                {3,3,3,3,3}
        }));
    }
}
