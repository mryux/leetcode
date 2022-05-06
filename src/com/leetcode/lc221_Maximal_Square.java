package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc221_Maximal_Square {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int ans = 0;

        for (int c = 0; c < cols; c++) {
            dp[0][c] = (matrix[0][c] == '1') ? 1 : 0;
            if (dp[0][c] == 1)
                ans = 1;
        }
        for (int r = 0; r < rows; r++) {
            dp[r][0] = (matrix[r][0] == '1') ? 1 : 0;
            if (dp[r][0] == 1)
                ans = 1;
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (matrix[r][c] == '1') {
                    dp[r][c] = Math.min(Math.min(dp[r-1][c-1], dp[r-1][c]), dp[r][c-1]) + 1;

                    ans = Math.max(ans, dp[r][c]);
                }
            }
        }

        return ans*ans;
    }
    
    @Test
    public void test01() {
        lc221_Maximal_Square solu = new lc221_Maximal_Square();

        Assertions.assertEquals(16, solu.maximalSquare(new char[][] {
                {'1','1','1','1','0'},
                {'1','1','1','1','0'},
                {'1','1','1','1','1'},
                {'1','1','1','1','1'},
                {'0','0','1','1','1'}
        }));
        Assertions.assertEquals(4, solu.maximalSquare(new char[][] {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        }));
        Assertions.assertEquals(1, solu.maximalSquare(new char[][] {
                {'0','1'},
                {'1','0'},
        }));
        Assertions.assertEquals(0, solu.maximalSquare(new char[][] {
                {'0'}
        }));
    }
}
