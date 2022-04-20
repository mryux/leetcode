package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class lc523_Continuous_Subarray_Sum {
    // Given an integer array nums and an integer k,
    // return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k,
    // or false otherwise.
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2)
            return false;

        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i-1];

            if ((nums[i] % k == 0) || (i >= 2 && nums[i] - nums[i-2] == 0))
                return true;

            if (nums[i] > k) {
                for (int j = 0; j < i-1; j++) {
                    if ((nums[i] - nums[j]) % k == 0)
                        return true;
                }
            }
        }

        return false;
    }

    public boolean checkSubarraySum2(int[] nums, int k) {
        if (nums == null || nums.length < 2)
            return false;

        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;

        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            int m = sum;
            if (k != 0)
                m %= k;

            if (map.containsKey(m)) {
                if (i - map.get(m) > 1)
                    return true;
            }
            else
                map.put(m, i);
        }

        return false;
    }

    @Test
    public void test01() {
        lc523_Continuous_Subarray_Sum solu = new lc523_Continuous_Subarray_Sum();

        Assertions.assertFalse(solu.checkSubarraySum(new int[]{0,1,0,3,0,4,0,4,0}, 5));
        Assertions.assertTrue(solu.checkSubarraySum(new int[]{23,2,4,6,6}, 7));
        Assertions.assertTrue(solu.checkSubarraySum(new int[]{23,2,4,6,7}, 6));
        Assertions.assertTrue(solu.checkSubarraySum(new int[]{23,2,4,6,7}, 13));
        Assertions.assertTrue(solu.checkSubarraySum(new int[]{23,2,6,4,7}, 6));
    }
}
