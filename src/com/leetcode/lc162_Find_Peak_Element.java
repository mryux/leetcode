package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc162_Find_Peak_Element {
    public int findPeakElement(int[] nums) {
        int N = nums.length;

        if (N < 2)
            return 0;

        if (nums[0] > nums[1])
            return 0;
        if (nums[N-1] > nums[N-2])
            return N-1;

        int l = 1;
        int r = N-2;

        while (l < r) {
            int m = (l+r)/2;

            if (nums[m] > nums[m-1] && nums[m] > nums[m+1])
                return m;

            if (nums[m] > nums[m+1])
                r = m-1;
            else
                l = m+1;
        }

        return r;
    }

    @Test
    public void test01() {
        Assertions.assertEquals(1, findPeakElement(new int[]{3,4,3,2,1}));
        Assertions.assertEquals(2, findPeakElement(new int[]{1,2,3}));
        Assertions.assertEquals(2, findPeakElement(new int[]{1,2,3,1}));

        int idx = findPeakElement(new int[]{1,2,1,3,5,6,4});
        Assertions.assertTrue(idx == 1 || idx == 5);
    }
}
