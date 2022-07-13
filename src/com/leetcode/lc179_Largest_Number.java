package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class lc179_Largest_Number {
    public String largestNumber(int[] nums) {
        Integer[] arr = new Integer[nums.length];

        for (int i = 0; i < arr.length; i++)
            arr[i] = nums[i];

        Arrays.sort(arr, (n1, n2) -> {
            return -(n1+""+n2).compareTo(n2+""+n1);
        });

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            builder.append(arr[i]);
        }

        String ans = builder.toString();

        if (ans.charAt(0) == '0')
            return "0";

        return ans;
    }

    @Test
    public void test01() {
        Assertions.assertEquals("0", largestNumber(new int[]{0, 0}));
        Assertions.assertEquals("210", largestNumber(new int[]{10, 2}));
        Assertions.assertEquals("9534330", largestNumber(new int[]{3,30,34,5,9}));
    }
}
