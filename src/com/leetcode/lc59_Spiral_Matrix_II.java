package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc59_Spiral_Matrix_II {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int top = 0;
        int bottom = n-1;
        int left = 0;
        int right = n-1;

        int v = 1;
        while (top <= bottom) {
            v = visit(matrix, left++, top++, right--, bottom--, v);
        }

        return matrix;
    }

    private int visit(int[][] matrix, int left, int top, int right, int bottom, int start) {
        if (top == bottom) {
            matrix[top][left] = start++;
        }
        else {
            // fill top row
            for (int i = left; i < right; i++) {
                matrix[top][i] = start++;
            }
            // fill right column
            for (int i = top; i < bottom; i++) {
                matrix[i][right] = start++;
            }
            // fill bottom row
            for (int i = right; i > left; i--) {
                matrix[bottom][i] = start++;
            }
            // fill left column
            for (int i = bottom; i > top; i--) {
                matrix[i][left] = start++;
            }
        }

        return start;
    }

    @Test
    public void test01() {
        lc59_Spiral_Matrix_II solu = new lc59_Spiral_Matrix_II();
        int[][] res = solu.generateMatrix(3);

        Assertions.assertArrayEquals(new int[]{1,2,3}, res[0]);
        Assertions.assertArrayEquals(new int[]{8,9,4}, res[1]);
        Assertions.assertArrayEquals(new int[]{7,6,5}, res[2]);
    }
}
