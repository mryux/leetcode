package com.lintcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc562_Backpack_IV {
    /*
    Give n items and an array, num[i] indicate the size of ith item. An integer target denotes the size of backpack. Find the number of ways to fill the backpack.
    Each item may be chosen unlimited number of times
     */
    public int backPackIV(int[] nums, int target) {
//        return visit(nums, 0, target);
        return visit_dp(nums, target);
    }

    private int visit_dp(int[] nums, int target) {
        int N = nums.length;
        int[] dp = new int[target+1];

        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= target; j++) {
                dp[j] = dp[j] + (j >= nums[i] ? dp[j-nums[i]] : 0);
            }
        }

        return dp[target];
    }

    private int visit_dp2(int[] nums, int target) {
        int N = nums.length;
        int[][] dp = new int[N][target+1];

        for (int i = nums[0]; i <= target; i++) {
            dp[0][i] = (i % nums[0] == 0) ? 1 : 0;
        }
        for (int i = 0; i < nums.length; i++)
            dp[i][0] = 1;

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i-1][j] + (j >= nums[i] ? dp[i][j-nums[i]] : 0);
            }
        }

        return dp[N-1][target];
    }

    private int visit(int[] nums, int curr, int target) {
        if (target == 0)
            return 1;

        if (curr >= nums.length)
            return 0;

        int ans = 0;

        for (int j = 0; j <= target; j += nums[curr])
            ans += visit(nums, curr+1, target-j);

        return ans;
    }

    @Test
    public void test01() {
        lc562_Backpack_IV solu = new lc562_Backpack_IV();

        Assertions.assertEquals(2, solu.backPackIV(new int[]{2,3,6,7}, 7));
        Assertions.assertEquals(3, solu.backPackIV(new int[]{2,3,4,5}, 7));
    }
}
