package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc41_First_Missing_Positive {
    public int firstMissingPositive(int[] nums) {
        int l = 0;
        int r = nums.length;

        while (l < r) {
            if (nums[l] == l+1)
                l++;
            else if (nums[l] <= l || nums[l] > r || nums[nums[l]-1] == nums[l])
                swap(nums, l, --r);
            else
                swap(nums, l, nums[l]-1);
        }

        return l+1;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void test01() {
        lc41_First_Missing_Positive solu = new lc41_First_Missing_Positive();

        Assertions.assertEquals(2, solu.firstMissingPositive(new int[]{1}));
        Assertions.assertEquals(3, solu.firstMissingPositive(new int[]{1,2,0}));
        Assertions.assertEquals(2, solu.firstMissingPositive(new int[]{3,4,-1,1}));
        Assertions.assertEquals(1, solu.firstMissingPositive(new int[]{7,8,9,11,12}));
    }
}
