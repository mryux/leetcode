package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc53_Maximum_Subarray {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int v : nums) {
            sum += v;
            if (sum > max)
                max = sum;
            if (sum < 0)
                sum = 0;
        }

        return max;
    }

    @Test
    public void test01() {
        lc53_Maximum_Subarray solu = new lc53_Maximum_Subarray();

        Assertions.assertEquals(-1, solu.maxSubArray(new int[]{-1,-2,-4}));
        Assertions.assertEquals(6, solu.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        Assertions.assertEquals(1, solu.maxSubArray(new int[]{1}));
        Assertions.assertEquals(23, solu.maxSubArray(new int[]{5,4,-1,7,8}));
    }
}
