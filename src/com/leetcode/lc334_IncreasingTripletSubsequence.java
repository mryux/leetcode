package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc334_IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= first) {
                first = nums[i];
            }
            else if (nums[i] <= second) {
                second = nums[i];
            }
            else {
                return true;
            }
        }

        return false;
    }

    @Test
    public void Test01() {
        lc334_IncreasingTripletSubsequence solu = new lc334_IncreasingTripletSubsequence();

        Assertions.assertFalse(solu.increasingTriplet(new int[]{30,40,20,35,38}));

    }
}
