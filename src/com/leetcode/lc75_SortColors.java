package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc75_SortColors {
    public void sortColors(int[] nums) {
        int rightOf0 = -1;
        int leftOf2 = nums.length;
        int i = 0;

        while (i < leftOf2) {
            if (nums[i] == 0) {
                swap(nums, i++, ++rightOf0);
            }
            else if (nums[i] == 1) {
                i++;
            }
            else {
                swap(nums, i, --leftOf2);
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];

        nums[a] = nums[b];
        nums[b] = tmp;
    }

    @Test
    public void test01() {
        lc75_SortColors solu = new lc75_SortColors();
        int[] arr = new int[]{2,0,2,1,1,0};

        solu.sortColors(arr);
        Assertions.assertArrayEquals(new int[]{0,0,1,1,2,2}, arr);
    }
}
