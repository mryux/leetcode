package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc48_RotateImage {
    public void rotate(int[][] matrix) {
        int top = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;

        while (top < bottom) {
            visit(matrix, left++, top++, right--, bottom--);
        }
    }

    private void visit(int[][] matrix, int left, int top, int right, int bottom) {
        for (int i = 0; i < right - left; i++) {
            int tmp = matrix[top][left+i];

            matrix[top][left+i] = matrix[bottom-i][left];
            matrix[bottom-i][left] = matrix[bottom][right-i];
            matrix[bottom][right-i] = matrix[top+i][right];
            matrix[top+i][right] = tmp;
        }
    }

    @Test
    public void test01() {
        lc48_RotateImage solu = new lc48_RotateImage();
        int[][] matrix =new int[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        solu.rotate(matrix);

        Assertions.assertArrayEquals(new int[]{7,4,1}, matrix[0]);
        Assertions.assertArrayEquals(new int[]{8,5,2}, matrix[1]);
        Assertions.assertArrayEquals(new int[]{9,6,3}, matrix[2]);
    }

    @Test
    public void test02() {
        lc48_RotateImage solu = new lc48_RotateImage();
        int[][] matrix =new int[][] {
                {5,1,9,11},
                {2,4,8,10},
                {13,3,6,7},
                {15,14,12,16}
        };
        solu.rotate(matrix);

        Assertions.assertArrayEquals(new int[]{15,13,2,5}, matrix[0]);
        Assertions.assertArrayEquals(new int[]{14,3,4,1}, matrix[1]);
        Assertions.assertArrayEquals(new int[]{12,6,8,9}, matrix[2]);
        Assertions.assertArrayEquals(new int[]{16,7,10,11}, matrix[3]);
    }
}
