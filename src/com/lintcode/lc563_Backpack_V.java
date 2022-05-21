package com.lintcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc563_Backpack_V {
    /**
     * @param nums: an integer array and all positive numbers
     * @param target: An integer
     * @return: An integer
     */
    public int backPackV(int[] nums, int target) {
//        return visit(nums, 0, target);
        return visit_dp(nums, target);
    }

    private int visit_dp(int[] nums, int target) {
        int[][] dp = new int[nums.length+1][target+1];

        for (int i = 0; i < dp.length; i++)
            dp[i][0] = 1;

        for (int i = nums.length-1; i >= 0; i--) {
            for (int j = 0; j <= target; j++) {
                int no = dp[i+1][j];
                int yes = j >= nums[i] ? dp[i+1][j-nums[i]] : 0;

                dp[i][j] = no + yes;
            }
        }

        return dp[0][target];
    }

    private int visit(int[] nums, int curr, int target) {
        if (target == 0)
            return 1;

        if (curr >= nums.length)
            return 0;

        int no = visit(nums, curr+1, target);
        int yes = target >= nums[curr] ? visit(nums, curr+1, target - nums[curr]) : 0;

        return no + yes;
    }

    @Test
    public void test01() {
        lc563_Backpack_V solu = new lc563_Backpack_V();

        Assertions.assertEquals(1, solu.backPackV(new int[]{7}, 7));
        Assertions.assertEquals(2, solu.backPackV(new int[]{1,2,3,3,7}, 7));
    }
}
