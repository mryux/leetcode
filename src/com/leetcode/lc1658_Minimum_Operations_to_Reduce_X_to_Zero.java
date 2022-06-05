package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class lc1658_Minimum_Operations_to_Reduce_X_to_Zero {
    public int minOperations(int[] nums, int x) {
        int len = nums.length;
        //Map<String, Integer> map = new HashMap<>();

        //return visit(nums, 0, len-1, x, map);
        return visit(nums, x);
    }

    private int visit(int[] nums, int target) {
        int sum = 0;
        for (int v : nums) {
            sum += v;
        }

        target = sum - target;
        if (target < 0)
            return -1;

        if (target == 0)
            return nums.length;

        int l = 0;
        int r = 0;
        int ans = Integer.MAX_VALUE;

        sum = 0;
        while (r < nums.length) {
            sum += nums[r++];

            while (sum > target) {
                sum -= nums[l++];
            }

            if (sum == target) {
                ans = Math.min(ans, nums.length - (r - l));
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int visit(int[] nums, int l, int r, int target, Map<String, Integer> dp) {
        if (target < 0) {
            return -1;
        }

        if (target == 0) {
            return 0;
        }

        if (l > r)
            return -1;

        String key = "" + l + "_" + r;

        if (dp.containsKey(key))
            return dp.get(key);

        int v1 = visit(nums, l+1, r, target - nums[l], dp);
        int v2 = visit(nums, l, r-1, target - nums[r], dp);
        int ans = 0;

        if (v1 == -1)
            ans = v2 == -1 ? -1 : v2+1;
        else if (v2 == -1)
            ans = v1 + 1;
        else
            ans = Math.min(v1, v2) + 1;

        dp.put(key, ans);
        return ans;
    }

    @Test
    public void test01() {
        lc1658_Minimum_Operations_to_Reduce_X_to_Zero solu = new lc1658_Minimum_Operations_to_Reduce_X_to_Zero();

        Assertions.assertEquals(16, solu.minOperations(new int[]{8828,9581,49,9818,9974,9869,9991,10000,10000,10000,9999,9993,9904,8819,1231,6309}, 134365));
        Assertions.assertEquals(-1, solu.minOperations(new int[]{1,1}, 3));
        Assertions.assertEquals(2, solu.minOperations(new int[]{1,1,4,2,3}, 5));
        Assertions.assertEquals(-1, solu.minOperations(new int[]{5,6,7,8,9}, 4));
        Assertions.assertEquals(5, solu.minOperations(new int[]{3,2,20,1,1,3}, 10));
    }
}
