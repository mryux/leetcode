package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class lc84_Largest_Rectangle_in_Histogram {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;

        int ans = 0;
        Stack<Integer> stack = new Stack<>();

        stack.push(0);
        for (int i = 1; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int k = stack.pop();
                int l = stack.isEmpty() ? -1 : stack.peek();

                ans = Math.max(ans, (i-l-1)*heights[k]);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int k = stack.pop();
            int l = stack.isEmpty() ? -1 : stack.peek();

            ans = Math.max(ans, (heights.length-l-1)*heights[k]);
        }

        return ans;
    }

    @Test
    public void test01() {
        lc84_Largest_Rectangle_in_Histogram solu = new lc84_Largest_Rectangle_in_Histogram();

        Assertions.assertEquals(10, solu.largestRectangleArea(new int[]{2,1,5,6,2,3}));
        Assertions.assertEquals(4, solu.largestRectangleArea(new int[]{2,4}));
    }
}
