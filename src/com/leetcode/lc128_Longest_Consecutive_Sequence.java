package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class lc128_Longest_Consecutive_Sequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int v : nums) {
            set.add(v);
        }

        int max = 0;
        for (int v : nums) {
            if (set.isEmpty())
                break;

            if (!set.contains(v))
                continue;

            int count = 0;
            int lessV = v;

            while (set.contains(lessV)) {
                set.remove(lessV);
                lessV--;
                count++;
            }

            int greaterV = v+1;
            while (set.contains(greaterV)) {
                set.remove(greaterV);
                greaterV++;
                count++;
            }

            if (count > max)
                max = count;
        }

        return max;
    }

    @Test
    public void test01() {
        lc128_Longest_Consecutive_Sequence solu = new lc128_Longest_Consecutive_Sequence();

        Assertions.assertEquals(4, solu.longestConsecutive(new int[]{100,4,200,1,3,2}));
        Assertions.assertEquals(9, solu.longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }
}
