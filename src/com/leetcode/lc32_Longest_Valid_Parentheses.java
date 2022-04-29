package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc32_Longest_Valid_Parentheses {
    public int longestValidParentheses(String s) {
        char[] arr = s.toCharArray();
        int[] dp = new int[arr.length];
        int ans = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == ')') {
                int pre = i - dp[i-1] -1;
                if (pre >= 0 && arr[pre] == '(') {
                    dp[i] = dp[i-1] + 2 + (pre >= 1 ? dp[pre-1] : 0);
                }
                ans = Math.max(ans, dp[i]);
            }
        }

        return ans;
    }

    @Test
    public void test01() {
        lc32_Longest_Valid_Parentheses solu = new lc32_Longest_Valid_Parentheses();

        Assertions.assertEquals(0, solu.longestValidParentheses("))"));
        Assertions.assertEquals(2, solu.longestValidParentheses("(()"));
        Assertions.assertEquals(2, solu.longestValidParentheses("()))"));
        Assertions.assertEquals(4, solu.longestValidParentheses("((())"));
        Assertions.assertEquals(4, solu.longestValidParentheses(")()())"));
        Assertions.assertEquals(8, solu.longestValidParentheses("(()(()())"));
    }
}
