package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class lc15_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        if (nums == null || nums.length == 0)
            return ans;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            if (nums[i] > 0)
                break;

            if (i == 0 || nums[i] != nums[i-1]) {
                int lo = i+1;
                int hi = nums.length-1;

                while (lo < hi) {
                    int val = nums[lo] + nums[hi];

                    if (val == -nums[i]) {
                        if (lo == i+1 || nums[lo] != nums[lo-1])
                            ans.add(List.of(nums[i], nums[lo], nums[hi]));
                        lo++;
                    }
                    else if (val < -nums[i])
                        lo++;
                    else
                        hi--;
                }
            }
        }

        return ans;
    }

    @Test
    public void test01() {
        lc15_3Sum solu = new lc15_3Sum();

        List<List<Integer>> ans = solu.threeSum(new int[]{-1,0,1,2,-1,-4});

        Assertions.assertEquals(2, ans.size());
        Assertions.assertTrue(ans.contains(List.of(-1,-1,2)));
        Assertions.assertTrue(ans.contains(List.of(-1,0,1)));
    }

    @Test
    public void test02() {
        lc15_3Sum solu = new lc15_3Sum();

        List<List<Integer>> ans = solu.threeSum(new int[]{-2,0,0,2,2});

        Assertions.assertEquals(1, ans.size());
        Assertions.assertTrue(ans.contains(List.of(-2,0,2)));
    }
}
