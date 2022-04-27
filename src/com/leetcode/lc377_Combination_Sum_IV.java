package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lc377_Combination_Sum_IV {
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);

        return visit(nums, target, new ArrayList<>());
    }

    private int visit(int[] nums, int rest, List<Integer> path) {
        if (rest == 0) {
            for (int v : path) {
                System.out.print(v + ",");
            }
            System.out.println();
            System.out.println("--------");
            return 1;
        }

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (rest - nums[i] >= 0) {
                path.add(nums[i]);
                ans += visit(nums, rest - nums[i], path);
                path.remove(path.size()-1);
            }
        }

        return ans;
    }

    public int combinationSum4_dp(int[] nums, int target) {
        int[] dp = new int[target+1];

        dp[0] = 1;
        for (int rest = 0; rest <= target; rest++) {
            for (int i = 0; i < nums.length; i++)
                if (rest - nums[i] >= 0)
                    dp[rest] += dp[rest - nums[i]];
        }

        return dp[target];
    }

    @Test
    public void test01() {
        lc377_Combination_Sum_IV solu = new lc377_Combination_Sum_IV();

        Assertions.assertEquals(7, solu.combinationSum4(new int[]{1,2,3}, 4));
        Assertions.assertEquals(7, solu.combinationSum4_dp(new int[]{1,2,3}, 4));
    }
}
