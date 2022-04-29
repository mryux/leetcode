package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc42_Trapping_Rain_Water {

    public int trap(int[] height) {
        if (height == null || height.length <= 2)
            return 0;

        int left = 0;
        int leftMax = height[left++];
        int right = height.length-1;
        int rightMax = height[right--];
        int ans = 0;

        while (left <= right) {
            if (leftMax < rightMax) {
                ans += Math.max(0, leftMax - height[left]);
                leftMax = Math.max(leftMax, height[left]);
                left++;
            }
            else {
                ans += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }

        return ans;
    }

    public int trap2(int[] height) {
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            max = Math.max(height[i], max);
            maxLeft[i] = max;
        }

        max = Integer.MIN_VALUE;
        for (int i = height.length-1; i >= 0; i--) {
            max = Math.max(height[i], max);
            maxRight[i] = max;
        }

        int ans = 0;
        for (int i = 1; i < height.length-1; i++) {
            int min = Math.min(maxLeft[i-1], maxRight[i+1]);
            ans += Math.max(0, min - height[i]);
        }

        return ans;
    }

    @Test
    public void test01() {
        lc42_Trapping_Rain_Water solu = new lc42_Trapping_Rain_Water();

        Assertions.assertEquals(6, solu.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
        Assertions.assertEquals(9, solu.trap(new int[] {4,2,0,3,2,5}));
    }
}
