package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class lc764_Largest_Plus_Sign {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 1;
            }
        }

        for (int i = 0; i < mines.length; i++)
            dp[mines[i][0]][mines[i][1]] = 0;


        for (int row = 0; row < n; row++) {
            int count = 0;

            for (int col = 0; col < n; col++) {
                if (dp[row][col] == 0)
                    count = 0;
                else
                    count++;
                dp[row][col] = count;
            }
        }

        for (int row = 0; row < n; row++) {
            int count = 0;

            for (int col = n-1; col >= 0; col--) {
                if (dp[row][col] == 0)
                    count = 0;
                else
                    count++;
                dp[row][col] = Math.min(dp[row][col], count);
            }
        }

        for (int col = 0; col < n; col++) {
            int count = 0;

            for (int row = 0; row < n; row++) {
                if (dp[row][col] == 0)
                    count = 0;
                else
                    count++;
                dp[row][col] = Math.min(dp[row][col], count);
            }
        }

        int ans = 0;
        for (int col = 0; col < n; col++) {
            int count = 0;

            for (int row = n-1; row >= 0; row--) {
                if (dp[row][col] == 0)
                    count = 0;
                else
                    count++;
                dp[row][col] = Math.min(dp[row][col], count);
                ans = Math.max(ans, dp[row][col]);
            }
        }

        return ans;
    }

    public int orderOfLargestPlusSign2(int n, int[][] mines) {
        boolean[][] field = new boolean[n][n];
        int[][] dpup = new int[n][n];
        int[][] dpdown = new int[n][n];
        int[][] dpleft = new int[n][n];
        int[][] dpright = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                field[i][j] = true;
                dpup[i][j] = 1;
                dpdown[i][j] = 1;
                dpleft[i][j] = 1;
                dpright[i][j] = 1;
            }
        }
        for (int i = 0; i < mines.length; i++) {
            int[] mine = mines[i];
            int r = mine[0];
            int c = mine[1];

            field[r][c] = false;
            dpup[r][c] = 0;
            dpdown[r][c] = 0;
            dpleft[r][c] = 0;
            dpright[r][c] = 0;
        }

        int ans = 0;
        for (int row = 1; row < field.length; row++) {
            for (int col = 1; col < field[0].length; col++) {
                if (field[row][col]) {
                    dpup[row][col] = dpup[row-1][col] + 1;
                    dpleft[row][col] = dpleft[row][col-1] + 1;
                }
            }
        }

        for (int row = field.length-2; row >= 0; row--) {
            for (int col = field[0].length-2; col >= 0; col--) {
                if (field[row][col]) {
                    dpdown[row][col] = dpdown[row+1][col] + 1;
                    dpright[row][col] = dpright[row][col+1] + 1;
                }
            }
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                ans = Math.max(ans, Math.min(Math.min(dpup[row][col], dpdown[row][col]), Math.min(dpleft[row][col], dpright[row][col])));
            }
        }

        return ans;
    }

    @Test
    public void test01() {
        lc764_Largest_Plus_Sign solu = new lc764_Largest_Plus_Sign();

        Assertions.assertEquals(4, solu.orderOfLargestPlusSign(10, new int[][] {
                {0,0},{0,1},{0,2},{0,7},{1,2},{1,3},{1,9},{2,3},{2,5},{2,7},{2,8},{3,2},{3,5},{3,7},{4,2},
                {4,3},{4,5},{4,7},{5,1},{5,4},{5,8},{5,9},{7,2},{7,5},{7,7},{7,8},{8,5},{8,8},{9,0},{9,1},{9,2},{9,8}
        }));
        Assertions.assertEquals(3, solu.orderOfLargestPlusSign(5, new int[][] {{3,0},{3,3}}));
        Assertions.assertEquals(1, solu.orderOfLargestPlusSign(2, new int[][] {{0,0},{0,1},{1,0}}));
        Assertions.assertEquals(2, solu.orderOfLargestPlusSign(5, new int[][] {{4,2}}));
        Assertions.assertEquals(0, solu.orderOfLargestPlusSign(1, new int[][] {{0,0}}));
    }
}
