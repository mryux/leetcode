package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc540_SingleElementInSortedArray {
    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length-1;

        while (l < r) {
            int mid = (l+r) >> 1;

            if ((mid - l) % 2 == 0) {
                // even numbers on left
                if (nums[mid] == nums[mid+1]) {
                    l = mid+2;
                }
                else if (nums[mid] == nums[mid-1]) {
                    r = mid-2;
                }
                else {
                    return nums[mid];
                }
            }
            else {
                if (nums[mid] == nums[mid-1]) {
                    l = mid+1;
                }
                else if (nums[mid] == nums[mid+1]) {
                    r = mid-1;
                }
                else {
                    return nums[mid];
                }
            }
        }

        return nums[l];
    }

    @Test
    public void test01() {
        lc540_SingleElementInSortedArray solu = new lc540_SingleElementInSortedArray();

        Assertions.assertEquals(2, solu.singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));
        Assertions.assertEquals(10, solu.singleNonDuplicate(new int[]{3,3,7,7,10,11,11}));
    }
}
