package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc33_Search_in_Rotated_Sorted_Array {
    public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length-1;

        while (lo <= hi) {
            int mid = (lo+hi)/2;

            if (nums[mid] == target)
                return mid;

            if (nums[mid] < nums[hi]) {
                if (nums[mid] < target && target < nums[hi])
                    lo = mid+1;
                else
                    hi = mid-1;
            } else {
                if (nums[lo] < target && target < nums[mid])
                    hi = mid-1;
                else
                    lo = mid+1;
            }
        }
        return -1;
    }

    @Test
    public void test01() {
        lc33_Search_in_Rotated_Sorted_Array solu = new lc33_Search_in_Rotated_Sorted_Array();

        Assertions.assertEquals(4, solu.search(new int[]{4,5,6,7,0,1,2}, 0));
        Assertions.assertEquals(-1, solu.search(new int[]{4,5,6,7,0,1,2}, 3));
        Assertions.assertEquals(-1, solu.search(new int[]{1}, 0));
    }
}
