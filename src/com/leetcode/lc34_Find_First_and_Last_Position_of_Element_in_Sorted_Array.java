package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc34_Find_First_and_Last_Position_of_Element_in_Sorted_Array {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[] {-1, -1};

        if (nums == null || nums.length == 0)
            return ans;

        int lo = 0;
        int hi = nums.length-1;

        while (lo <= hi) {
            int mid = (lo+hi)/2;

            if (nums[mid] < target)
                lo = mid+1;
            else
                hi = mid-1;
        }
        if (lo == nums.length || nums[lo] != target)
            return ans;

        ans[0] = lo;
        hi = nums.length-1;
        while (lo <= hi) {
            int mid = (lo+hi)/2;

            if (nums[mid] > target)
                hi = mid-1;
            else
                lo = mid+1;
        }
        ans[1] = hi;

        return ans;
    }

    @Test
    public void test01() {
        lc34_Find_First_and_Last_Position_of_Element_in_Sorted_Array solu = new lc34_Find_First_and_Last_Position_of_Element_in_Sorted_Array();

        Assertions.assertArrayEquals(new int[]{-1,-1}, solu.searchRange(new int[]{2,2}, 3));
        Assertions.assertArrayEquals(new int[]{3,4}, solu.searchRange(new int[]{5,7,7,8,8,10}, 8));
        Assertions.assertArrayEquals(new int[]{-1,-1}, solu.searchRange(new int[]{5,7,7,8,8,10}, 6));
        Assertions.assertArrayEquals(new int[]{-1,-1}, solu.searchRange(new int[]{}, 0));
    }
}
