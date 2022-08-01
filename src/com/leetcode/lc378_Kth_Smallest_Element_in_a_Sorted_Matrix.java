package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

public class lc378_Kth_Smallest_Element_in_a_Sorted_Matrix {
    class Node {
        public int val;
        public int row;
        public int col;

        public Node(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Node> que = new PriorityQueue<>((n1, n2)-> n1.val - n2.val);
        int N = matrix.length;
        int M = matrix[0].length;
        boolean[][] visited = new boolean[N][M];

        addToQueue(que, matrix, 0, 0, visited);
        while (!que.isEmpty()) {
            Node node = que.poll();

            if (--k == 0)
                return node.val;

            int row = node.row;
            int col = node.col;

            if (node.row+1 < N)
                addToQueue(que, matrix, row+1, col, visited);
            if (node.col+1 < M)
                addToQueue(que, matrix, row, col+1, visited);
        }

        return -1;
    }

    private void addToQueue(PriorityQueue<Node> que, int[][] matrix, int row, int col, boolean[][] visited) {
        if (visited[row][col])
            return;

        visited[row][col] = true;
        que.add(new Node(matrix[row][col], row, col));
    }

    public int kthSmallest02(int[][] matrix, int k) {
        int n = matrix.length;

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b) -> matrix[a[0]][a[1]] - matrix[b[0]][b[1]]);

        for(int i = 0 ; i < n ; i++)
            pq.offer(new int[]{i, 0});

        while(pq.size() > 0) {
            int[] polled = pq.poll();
            k--;
            if (k == 0)
                return matrix[polled[0]][polled[1]];

            if (polled[1] + 1 < n)
                pq.offer(new int[]{polled[0], polled[1] + 1});
        }

        return 0;
    }

    @Test
    public void test01() {
        Assertions.assertEquals(11, kthSmallest(new int[][]{
                {1,3,5},
                {6,7,12},
                {11,14,14}
        }, 6));
        Assertions.assertEquals(13, kthSmallest(new int[][]{
                {1,5,9},
                {10,11,13},
                {12,13,15}
        }, 8));
        Assertions.assertEquals(-5, kthSmallest(new int[][]{
                {-5},
        }, 1));
    }
}
