package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc191_Number_of_1_Bits {
    public int hammingWeight1(int n) {
        int count = 0;
        int rightOne = 0;

        while (n != 0) {
            count++;
            rightOne = n & (~n+1);
            n ^= rightOne;
        }

        return count;
    }

    public int hammingWeight(int n) {
        int sum = 0;

        while (n != 0) {
            sum++;
            n = n & (n - 1);
        }

        return sum;
    }

    @Test
    public void test01() {
        Assertions.assertEquals(3, hammingWeight(0b00000000000000000000000000001011));
        Assertions.assertEquals(1, hammingWeight(0b00000000000000000000000010000000));
        Assertions.assertEquals(31, hammingWeight(0b11111111111111111111111111111101));
    }
}
