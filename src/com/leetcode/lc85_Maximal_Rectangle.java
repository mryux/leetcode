package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class lc85_Maximal_Rectangle {
    public int maximalRectangle(char[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[] row = new int[M];

        for (int r1 = 0; r1 < N; r1++) {
            Arrays.fill(row, 0);
            // copy r1 row cells
            addRow(matrix[r1], row);
            int cols = findContinuousValues(row, 1);

            ans = Math.max(ans, 1*cols);

            for (int r2 = r1+1; r2 < N; r2++) {
                addRow(matrix[r2], row);

                // find longest equal values, break if no continuous equal values.
                int rows = r2-r1+1;
                cols = findContinuousValues(row, rows);

                ans = Math.max(ans, rows*cols);
            }
        }

        return ans;
    }

    private void addRow(char[] rowMatrix, int[] row) {
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
