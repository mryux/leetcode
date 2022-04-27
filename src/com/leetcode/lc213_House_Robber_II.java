package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc213_House_Robber_II {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        return Math.max(visit(nums, 0, nums.length-1, true), visit(nums, 0, nums.length, false));
    }

    private int visit(int[] nums, int idx, int end, boolean rob) {
        if (idx == end) {
            return 0;
        }

        if (rob)
            return nums[idx] + visit(nums, idx+1, end, false);

        return Math.max(visit(nums, idx+1, end, true), visit(nums, idx+1, end, false));
    }

    public int rob_dp(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        return Math.max(visit_dp(nums, 0, nums.length-1), visit_dp(nums, 1, nums.length));
    }

    private int visit_dp(int[] nums, int lo, int hi) {
        int[][] dp = new int[hi-lo+1][2];

        for (int i = hi-lo-1; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) {
                if (j == 1)
                    dp[i][j] = nums[i+lo] + dp[i+1][0];
                else
                    dp[i][j] = Math.max(dp[i+1][1], dp[i+1][0]);
            }
        }

        return Math.max(dp[0][1], dp[0][0]);
    }

    @Test
    public void test01() {
        lc213_House_Robber_II solu = new lc213_House_Robber_II();

        Assertions.assertEquals(3, solu.rob(new int[]{2,3,2}));
        Assertions.assertEquals(4, solu.rob(new int[]{1,2,3,1}));
        Assertions.assertEquals(11, solu.rob(new int[]{2,7,9,3,1}));
        Assertions.assertEquals(3, solu.rob_dp(new int[]{2,3,2}));
        Assertions.assertEquals(4, solu.rob_dp(new int[]{1,2,3,1}));
        Assertions.assertEquals(11, solu.rob_dp(new int[]{2,7,9,3,1}));
    }
}
