package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc198_House_Robber {
    public int rob(int[] nums) {
        return Math.max(visit(nums, 0, true), visit(nums, 0, false));
    }

    private int visit(int[] num, int idx, boolean rob) {
        if (idx == num.length) {
            return 0;
        }

        if (rob)
            return num[idx] + visit(num, idx+1, false);

        return Math.max(visit(num, idx+1, true), visit(num, idx+1, false));
    }

    public int rob_dp(int[] nums) {
        int[][] dp = new int[nums.length+1][2];

        for (int i = nums.length-1; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) {
                if (j == 1)
                    dp[i][j] = nums[i] + dp[i+1][0];
                else
                    dp[i][j] = Math.max(dp[i+1][1], dp[i+1][0]);
            }
        }

        return Math.max(dp[0][0], dp[0][1]);
    }

    @Test
    public void test01() {
        lc198_House_Robber solu = new lc198_House_Robber();

        Assertions.assertEquals(4, solu.rob(new int[]{1,2,3,1}));
        Assertions.assertEquals(12, solu.rob(new int[]{2,7,9,3,1}));
        Assertions.assertEquals(4, solu.rob_dp(new int[]{1,2,3,1}));
        Assertions.assertEquals(12, solu.rob_dp(new int[]{2,7,9,3,1}));
    }
}
