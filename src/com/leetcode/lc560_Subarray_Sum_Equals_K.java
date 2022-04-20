package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class lc560_Subarray_Sum_Equals_K {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum-k)) {
                ret += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }

        return ret;
    }

    @Test
    public void test01() {
        lc560_Subarray_Sum_Equals_K solu = new lc560_Subarray_Sum_Equals_K();

        Assertions.assertEquals(0, solu.subarraySum(new int[]{1}, 0));
        Assertions.assertEquals(2, solu.subarraySum(new int[]{1,1,1}, 2));
        Assertions.assertEquals(2, solu.subarraySum(new int[]{1,2,3}, 3));
        Assertions.assertEquals(2, solu.subarraySum(new int[]{1,-2,3}, 1));
        Assertions.assertEquals(3, solu.subarraySum(new int[]{1,-1,0}, 0));
    }
}
