package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer;
in other words, it is the product of some integer with itself.
For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 */
public class lc279_PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n+1];

        Arrays.fill(dp, n);
        dp[0] = 0;
        for (int target = 1; target <= n; target++) {
            for (int i = 1; i <= target; i++) {
                int square = i*i;

                if (square > target) {
                    break;
                }

                dp[target] = Math.min(dp[target], dp[target - square]+1);
            }
        }

        return dp[n];
    }

    @Test
    public void test01() {
        lc279_PerfectSquares solu = new lc279_PerfectSquares();

        Assertions.assertEquals(2, solu.numSquares(13));
        Assertions.assertEquals(3, solu.numSquares(12));
    }
}
