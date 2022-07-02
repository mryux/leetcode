package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc73_Set_Matrix_Zeroes {
    /*
    use 1 variable.
     */
    public void setZeroes(int[][] matrix) {
        boolean hasZeroCol0 = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;

                    if (j == 0)
                        hasZeroCol0 = true;
                    else
                        matrix[0][j] = 0;
                }
            }
        }

        for (int i = matrix.length-1; i >= 0; i--) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (hasZeroCol0) {
            for (int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
        }
    }

    /*
    uses 2 boolean variables.
     */
    public void setZeroes02(int[][] matrix) {
        boolean hasZeroRow0 = false;
        boolean hasZeroCol0 = false;

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                hasZeroRow0 = true;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                hasZeroCol0 = true;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (hasZeroRow0) {
            for (int i = 0; i < matrix[0].length; i++)
                matrix[0][i] = 0;
        }

        if (hasZeroCol0) {
            for (int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
        }
    }

    @Test
    public void test01() {
        lc73_Set_Matrix_Zeroes solu = new lc73_Set_Matrix_Zeroes();
        int[][] matrix = new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1},
        };

        solu.setZeroes(matrix);
        Assertions.assertArrayEquals(new int[]{1,0,1}, matrix[0]);
        Assertions.assertArrayEquals(new int[]{0,0,0}, matrix[1]);
        Assertions.assertArrayEquals(new int[]{1,0,1}, matrix[2]);
    }
}
