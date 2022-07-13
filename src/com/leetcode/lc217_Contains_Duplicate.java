package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class lc217_Contains_Duplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int v : nums) {
            if (!set.add(v))
                return true;
        }

        return false;
    }

    @Test
    public void test01() {
        Assertions.assertTrue(containsDuplicate(new int[]{1,2,3,1}));
        Assertions.assertFalse(containsDuplicate(new int[]{1,2,3,4}));
        Assertions.assertTrue(containsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2}));
    }
}
