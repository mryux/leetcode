package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class lc202_Happy_Number {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        while (true) {
            int next = 0;

            while (n > 0) {
                int c = n % 10;
                next += c*c;
                n /= 10;
            }

            if (next == 1)
                return true;

            if (set.contains(next))
                return false;

            set.add(next);
            n = next;
        }
    }

    @Test
    public void test01() {
        Assertions.assertTrue(isHappy(19));
        Assertions.assertFalse(isHappy(2));
    }
}
