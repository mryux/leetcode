package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc153_FindMinimuminRotatedSortedArray {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l)/2;

            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }
    public int findMin2(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            if (l+1 == r)
                return Math.min(nums[l],nums[r]);

            int mid = (l+r)>>1;

            if (mid-1 >= 0 ? nums[mid] < nums[mid-1] : true
                    && mid+1 <= nums.length-1 ? nums[mid] < nums[mid+1] : true)
                return nums[mid];

            if (nums[mid] > nums[l]) {
                if (nums[l] > nums[r]) {
                    l = mid;
                } else {
                    r = mid;
                }
            } else {
                if (nums[l] > nums[r]) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
        }

        return nums[l];
    }

    @Test
    public void test01() {
        lc153_FindMinimuminRotatedSortedArray solu = new lc153_FindMinimuminRotatedSortedArray();

        Assertions.assertEquals(1, solu.findMin(new int[] {3,4,5,6,7,1,2}));
        Assertions.assertEquals(1, solu.findMin(new int[] {4,5,6,7,1,2,3}));
        Assertions.assertEquals(1, solu.findMin(new int[] {5,6,7,1,2,3,4}));
        Assertions.assertEquals(1, solu.findMin(new int[] {6,7,1,2,3,4,5}));
        Assertions.assertEquals(1, solu.findMin(new int[] {7,1,2,3,4,5,6}));
        Assertions.assertEquals(1, solu.findMin(new int[] {1,2,3,4,5,6,7}));
        Assertions.assertEquals(1, solu.findMin(new int[] {2,3,4,5,6,7,1}));

//        Assertions.assertEquals(0, solu.findMin(new int[] {4,5,6,7,0,1,2}));
    }
}
