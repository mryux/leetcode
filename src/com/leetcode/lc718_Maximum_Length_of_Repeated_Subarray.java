package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc718_Maximum_Length_of_Repeated_Subarray {
    public int findLength(int[] nums1, int[] nums2) {
        return dp(nums1, nums2);
    }

    private int dp(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length];
        int ans = 0;

        for (int i = 0; i < nums2.length; i++) {
            dp[i] = (nums1[0] == nums2[i]) ? 1 : 0;
            ans = Math.max(ans, dp[i]);
        }

        for (int row = 1; row < nums1.length; row++) {
            for (int col = nums2.length-1; col > 0; col--) {
                if (nums1[row] == nums2[col]) {
                    dp[col] = dp[col-1] + 1;
                    ans = Math.max(ans, dp[col]);
                }
                else
                    dp[col] = 0;
            }

            dp[0] = nums1[row] == nums2[0] ? 1 : 0;
            ans = Math.max(ans, dp[0]);
        }

        return ans;
    }

    private int dp1(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];
        int ans = 0;

        for (int i = 0; i < nums1.length; i++) {
            dp[i][0] = (nums1[i] == nums2[0]) ? 1 : 0;
            ans = Math.max(ans, dp[i][0]);
        }
        for (int i = 0; i < nums2.length; i++) {
            dp[0][i] = (nums1[0] == nums2[i]) ? 1 : 0;
            ans = Math.max(ans, dp[0][i]);
        }

        for (int row = 1; row < nums1.length; row++) {
            for (int col = 1; col < nums2.length; col++) {
                if (nums1[row] == nums2[col]) {
                    dp[row][col] = dp[row-1][col-1] + 1;
                    ans = Math.max(ans, dp[row][col]);
                }
            }
        }

        return ans;
    }

    @Test
    public void test01() {
        lc718_Maximum_Length_of_Repeated_Subarray solu = new lc718_Maximum_Length_of_Repeated_Subarray();

        Assertions.assertEquals(3, solu.findLength(new int[]{1,0,0,0,1,0,0,1,0,0}, new int[]{0,1,1,1,0,1,1,1,0,0}));
        Assertions.assertEquals(3, solu.findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}));
        Assertions.assertEquals(5, solu.findLength(new int[]{0,0,0,0,0}, new int[]{0,0,0,0,0}));
        Assertions.assertEquals(1, solu.findLength(new int[]{1,2,3,2,8}, new int[]{5,6,1,4,7}));
    }
}
