package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc329_Longest_Increasing_Path_in_a_Matrix {
    public int longestIncreasingPath(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        int ans = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, visit(matrix, i, j, Integer.MIN_VALUE, dp));
            }
        }

        return ans;
    }

    private int visit(int[][] matrix, int row, int col, int lastVal, int[][] dp) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length)
            return 0;

        if (matrix[row][col] <= lastVal)
            return 0;

        if (dp[row][col] > 0)
            return dp[row][col];

        int currVal = matrix[row][col];

        int max = visit(matrix, row+1, col, currVal, dp);
        max = Math.max(max, visit(matrix, row-1, col, currVal, dp));
        max = Math.max(max, visit(matrix, row, col+1, currVal, dp));
        max = Math.max(max, visit(matrix, row, col-1, currVal, dp));
        dp[row][col] = 1+max;

        return dp[row][col];
    }

    @Test
    public void test01() {
        Assertions.assertEquals(4, longestIncreasingPath(new int[][] {
                {9,9,4},
                {6,6,8},
                {2,1,1}
        }));
        Assertions.assertEquals(4, longestIncreasingPath(new int[][] {
                {3,4,5},
                {3,2,6},
                {2,2,1}
        }));
        Assertions.assertEquals(1, longestIncreasingPath(new int[][] {
                {1}
        }));
    }
}
