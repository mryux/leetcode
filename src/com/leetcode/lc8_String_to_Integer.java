package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc8_String_to_Integer {
    public int myAtoi(String s) {
        char[] arr = s.toCharArray();
        int start = 0;

        while (start < arr.length && arr[start] == ' ')
            start++;

        if (start == arr.length)
            return 0;

        boolean isPositive = true;
        if (arr[start] == '+') {
            start++;
        }
        else if (arr[start] == '-') {
            isPositive = false;
            start++;
        }
        else if (!isNumeric(arr[start]))
            return 0;

        int mm = Integer.MIN_VALUE / 10;
        int mod = Integer.MIN_VALUE % 10;
        int ans = 0;

        while (start < arr.length) {
            if (!isNumeric(arr[start]))
                break;

            int v = '0' - arr[start];
            if (ans < mm || (ans == mm && v < mod))
                return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            ans = ans * 10 + v;
            start++;
        }

        if (isPositive && ans == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;

        return isPositive ? -ans : ans;
    }

    private boolean isNumeric(char c) {
        return c >= '0' && c <= '9';
    }

    @Test
    public void test01() {
        lc8_String_to_Integer solu = new lc8_String_to_Integer();

        Assertions.assertEquals(-2147483648, solu.myAtoi("-2147483649"));
        Assertions.assertEquals(0, solu.myAtoi("  "));
        Assertions.assertEquals(0, solu.myAtoi(" ab 42"));
        Assertions.assertEquals(42, solu.myAtoi("42"));
        Assertions.assertEquals(-42, solu.myAtoi("   -42"));
        Assertions.assertEquals(4193, solu.myAtoi("4193 with words"));
    }
}
