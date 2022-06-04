package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc209_Minimum_Size_Subarray_Sum {
    /*
    Given an array of positive integers nums and a positive integer target,
    return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr]
    of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.
     */
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0;
        int r = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;

        while (r < nums.length) {
            sum += nums[r++];

            while (sum >= target) {
                ans = Math.min(ans, r - l);
                sum -= nums[l++];
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    @Test
    public void test01() {
        lc209_Minimum_Size_Subarray_Sum solu = new lc209_Minimum_Size_Subarray_Sum();

        Assertions.assertEquals(2, solu.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        Assertions.assertEquals(1, solu.minSubArrayLen(4, new int[]{1,4,4}));
        Assertions.assertEquals(0, solu.minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1}));
        Assertions.assertEquals(3, solu.minSubArrayLen(11, new int[]{1,2,3,4,5}));
    }
}
