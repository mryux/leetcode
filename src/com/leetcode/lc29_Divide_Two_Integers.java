package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc29_Divide_Two_Integers {
    public int divide(int dividend, int divisor) {
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }

        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1)
                return Integer.MAX_VALUE;

            int r = div(dividend+1, divisor);
            return r + div(dividend - r * divisor, divisor);
        }

        return div(dividend, divisor);
    }

    private int div(int dividend, int divisor) {
        int res = 0;
        boolean positive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);

        dividend = dividend > 0 ? dividend : -dividend;
        divisor = divisor > 0 ? divisor : -divisor;
        for (int i = 31; i >= 0; i--) {
            if ((dividend >> i) >= divisor) {
                res |= 1 << i;
                dividend -= divisor << i;
            }
        }
        return positive ? res : -res;
    }

    @Test
    public void test01() {
        lc29_Divide_Two_Integers solu = new lc29_Divide_Two_Integers();

        Assertions.assertEquals(3, solu.divide(10, 3));
        Assertions.assertEquals(-2, solu.divide(7, -3));
        Assertions.assertEquals(Integer.MIN_VALUE, solu.divide(Integer.MIN_VALUE, 1));
    }
}
