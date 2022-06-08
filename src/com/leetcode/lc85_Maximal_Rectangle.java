package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

public class lc85_Maximal_Rectangle {
    public int maximalRectangle(char[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int[] row = new int[matrix[0].length];

        for (int r = 0; r < N; r++) {
            addRow(matrix[r], row);
            ans = Math.max(ans, findMaxRectangle(row));
        }

        return ans;
    }

    private int findMaxRectangle(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int currArea = (i-k-1) * height[j];

                ans = Math.max(ans, currArea);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int currArea = (height.length-k-1) * height[j];

            ans = Math.max(ans, currArea);
        }

        return ans;
    }

    private void addRow(char[] rowMatrix, int[] row) {
        for (int i = 0; i < rowMatrix.length; i++) {
            int val = rowMatrix[i] - '0';

            if (val == 0)
                row[i] = 0;
            else
                row[i] += val;
        }
    }

    public int maximalRectangle01(char[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[] row = new int[M];

        for (int r1 = 0; r1 < N; r1++) {
            Arrays.fill(row, 0);
            // copy r1 row cells
            addRow01(matrix[r1], row);
            int cols = findContinuousValues(row, 1);

            ans = Math.max(ans, 1*cols);

            for (int r2 = r1+1; r2 < N; r2++) {
                addRow01(matrix[r2], row);

                // find longest equal values, break if no continuous equal values.
                int rows = r2-r1+1;
                cols = findContinuousValues(row, rows);
                if (cols == 0)
                    break;

                ans = Math.max(ans, rows*cols);
            }
        }

        return ans;
    }

    private void addRow01(char[] rowMatrix, int[] row) {
        for (int i = 0; i < rowMatrix.length; i++) {
            row[i] += rowMatrix[i] - '0';
        }
    }

    private int findContinuousValues(int[] row, int expect) {
        int ret = 0;
        int count = 0;

        for (int v : row) {
            if (v == expect) {
                count++;
            }
            else {
                ret = Math.max(ret, count);
                count = 0;
            }
        }

        return Math.max(ret, count);
    }

    @Test
    public void test01() {
        lc85_Maximal_Rectangle solu = new lc85_Maximal_Rectangle();

        Assertions.assertEquals(1, solu.maximalRectangle(new char[][]{
                {'0','1'},
                {'1','0'}
        }));
        Assertions.assertEquals(0, solu.maximalRectangle(new char[][]{
                {'0'}
        }));
        Assertions.assertEquals(1, solu.maximalRectangle(new char[][]{
                {'1'}
        }));
        Assertions.assertEquals(6, solu.maximalRectangle(new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        }));
    }
}
