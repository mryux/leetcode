package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class lc322_Coin_Change {
    public int coinChange2dp(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0)
            return -1;

        int N = coins.length;
        int[][] dp = new int[N][amount+1];

        for (int j = 1; j <= amount; j++) {
            if (j % coins[0] == 0)
                dp[0][j] = j / coins[0];
            else
                dp[0][j] = -1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                if (dp[i-1][j] != -1)
                    dp[i][j] = dp[i-1][j];

                if (j - coins[i] >= 0 && dp[i][j-coins[i]] != -1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j-coins[i]]+1);
                }
                if (dp[i][j] == Integer.MAX_VALUE)
                    dp[i][j] = -1;
            }
        }

        return dp[N-1][amount];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];

        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int m = coin; m <= amount; m++) {
                dp[m] = Math.min(dp[m], dp[m-coin]+1);
            }
        }

        return (dp[amount] > amount) ? -1 : dp[amount];
    }

    @Test
    public void test01() {
        Assertions.assertEquals(3, coinChange(new int[]{1,2,5}, 11));
        Assertions.assertEquals(-1, coinChange(new int[]{2}, 3));
        Assertions.assertEquals(0, coinChange(new int[]{1}, 0));
    }
}
