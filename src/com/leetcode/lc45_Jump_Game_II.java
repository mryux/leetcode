package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc45_Jump_Game_II {
    public int jump(int[] nums) {
        int jumps = 0;
        int curr = 0;
        int next = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (curr < i) {
                jumps++;
                curr = next;
            }
            next = Math.max(next, nums[i]+i);
        }

        return jumps;
    }

    @Test
    public void test01() {
        lc45_Jump_Game_II solu = new lc45_Jump_Game_II();

        Assertions.assertEquals(2, solu.jump(new int[]{2,3,1,1,4}));
        Assertions.assertEquals(2, solu.jump(new int[]{2,3,0,1,4}));
    }
}
