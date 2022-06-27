package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc55_Jump_Game {
    public boolean canJump(int[] nums) {
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (max < i)
                return false;

            max = Math.max(max, i+nums[i]);
        }

        return true;
    }

    @Test
    public void test01() {
        lc55_Jump_Game solu = new lc55_Jump_Game();

        Assertions.assertTrue(solu.canJump(new int[]{2,0,0}));
        Assertions.assertFalse(solu.canJump(new int[]{0,1}));
        Assertions.assertTrue(solu.canJump(new int[]{2,3,1,1,4}));
        Assertions.assertFalse(solu.canJump(new int[]{3,2,1,0,4}));
    }
}
