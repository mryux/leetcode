package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc7_Reverse_Integer {
    /*
    use negative number to handle case of MAX int.
     */
    public int reverse(int x) {
        boolean isPositive = x > 0;
        int m = Integer.MIN_VALUE / 10;
        int n = Integer.MIN_VALUE % 10;
        int val = 0;

        if (isPositive)
            x = -x;
        while (x != 0) {
            int mod = x % 10;

            if (val < m || (val == m && mod < n))
                return 0;

            val = val * 10 + mod;
            x = x / 10;
        }

        if (isPositive && val == Integer.MIN_VALUE)
            return 0;

        return isPositive ? -val : val;
    }

    @Test
    public void test01() {
        lc7_Reverse_Integer solu = new lc7_Reverse_Integer();

        Assertions.assertEquals(321, solu.reverse(123));
        Assertions.assertEquals(-321, solu.reverse(-123));
        Assertions.assertEquals(21, solu.reverse(120));
        Assertions.assertEquals(0, solu.reverse(1534236469));
    }
}
