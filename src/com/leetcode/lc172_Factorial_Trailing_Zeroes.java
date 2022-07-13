package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc172_Factorial_Trailing_Zeroes {
    public int trailingZeroes(int n) {
        int ans = 0;

        while (n > 0) {
            ans += n/5;
            n /= 5;
        }

        return ans;
    }

    @Test
    public void test01() {
        Assertions.assertEquals(0, trailingZeroes(3));
        Assertions.assertEquals(1, trailingZeroes(5));
        Assertions.assertEquals(0, trailingZeroes(0));
    }
}
