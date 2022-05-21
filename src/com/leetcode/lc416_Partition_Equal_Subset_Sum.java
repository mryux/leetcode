package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc416_Partition_Equal_Subset_Sum {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2)
            return false;

        int sum = 0;

        for (int v : nums) {
            sum += v;
        }
        if (sum % 2 == 1)
            return false;

//        return visit(nums, 0, sum/2);
        return visit_dp(nums, sum/2);
    }

    private boolean visit_dp(int[] num, int target) {
        boolean[][] dp = new boolean[num.length+1][target+1];

        for (int i = num.length-1; i >= 0; i--) {
            for (int j = 0; j <= target; j++) {
                if (num[i] == j) {
                    dp[i][j] = true;
                } else {
                    boolean no = dp[i+1][j];
                    boolean yes = num[i] < j ? dp[i+1][j-num[i]] : false;

                    dp[i][j] = no || yes;
                }
            }
        }

        return dp[0][target];
    }

    private boolean visit(int[] nums, int curr, int target) {
        if (curr >= nums.length)
            return false;

        if (target == nums[curr])
            return true;

        boolean no = visit(nums, curr+1, target);
        boolean yes = false;

        if (nums[curr] < target)
            yes = visit(nums, curr+1, target-nums[curr]);

        return no || yes;
    }

    @Test
    public void test01() {
        lc416_Partition_Equal_Subset_Sum solu = new lc416_Partition_Equal_Subset_Sum();

        Assertions.assertTrue(solu.canPartition(new int[]{1,5,11,5}));
        Assertions.assertFalse(solu.canPartition(new int[]{1,2,3,5}));
        Assertions.assertTrue(solu.canPartition(new int[]{2,2,1,1}));
        Assertions.assertTrue(solu.canPartition(new int[]{14,9,8,4,3,2}));
    }
}
