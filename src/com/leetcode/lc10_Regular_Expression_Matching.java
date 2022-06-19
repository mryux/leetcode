package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc10_Regular_Expression_Matching {
    public boolean isMatch(String s, String p) {
        char[] arr = s.toCharArray();
        char[] pattern = p.toCharArray();

        return validateExpr(arr, pattern)
                && visit_dp(arr, pattern);
    }

    public boolean isMatch01(String s, String p) {
        if (s == null || p == null) return false;

        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;

        for (int i = 1; i <= p.length(); i++){
            if (p.charAt(i - 1) == '*' && (i == 1 || p.charAt(i - 2) == '*'))
                return false;

            for (int j = 0; j <= s.length(); j++) {
                if (j == 0) {
                    if (p.charAt(i - 1) == '*')
                        dp[i][0] = dp[i - 2][0];
                    continue;
                }

                if (s.charAt(j - 1) == p.charAt(i - 1) || p.charAt(i - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*') {
                    if (p.charAt(i - 2) == '.' || s.charAt(j - 1) == p.charAt(i - 2)){
                        dp[i][j] = dp[i][j - 1] || dp[i - 2][j];
                    } else {
                        dp[i][j] = dp[i - 2][j];
                    }
                }
            }
        }

        return dp[p.length()][s.length()];
    }

    private boolean validateExpr(char[] arr, char[] pattern) {
        for (char v : arr) {
            if (v == '*' || v == '.')
                return false;
        }

        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == '*' && (i == 0 || pattern[i-1] == '*'))
                return false;
        }
        return true;
    }

    private boolean visit_dp(char[] arr, char[] pattern) {
        int N = arr.length;
        int M = pattern.length;
        boolean[][] dp = new boolean[N+1][M+1];

        // init dp
        dp[N][M] = true;
        for (int j = M-2; j >= 0; j-=2) {
            if (pattern[j] != '*' && pattern[j+1] == '*')
                dp[N][j] = true;
            else
                break;
        }
        dp[N-1][M-1] = (arr[N-1] == pattern[M-1] || '.' == pattern[M-1]);

        for (int i = N-1; i >= 0; i--) {
            for (int j = M-2; j >= 0; j--) {
                if (pattern[j+1] != '*') {
                    dp[i][j] = (arr[i] == pattern[j] || pattern[j] == '.') && dp[i+1][j+1];
                }
                else {
                    int si = i;
                    while (si < arr.length && (arr[si] == pattern[j] || pattern[j] == '.')) {
                        if (dp[si][j+2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (!dp[i][j])
                        dp[i][j] = dp[si][j+2];
                }
            }
        }

        return dp[0][0];
    }

    private boolean visit(char[] arr, int si, char[] pattern, int pi) {
        if (pi == pattern.length)
            return si == arr.length;

        if (pi+1 == pattern.length || pattern[pi+1] != '*') {
            return si < arr.length
                    && (arr[si] == pattern[pi] || pattern[pi] == '.')
                    && visit(arr, si+1, pattern, pi+1);
        }

        // patter[pi+1] == '*'
        while (si < arr.length && (pattern[pi] == arr[si] || pattern[pi] == '.')) {
            if (visit(arr, si, pattern, pi+2))
                return true;
            si++;
        }

        return visit(arr, si, pattern, pi+2);
    }

    @Test
    public void test01() {
        lc10_Regular_Expression_Matching solu = new lc10_Regular_Expression_Matching();

        Assertions.assertFalse(solu.isMatch("mississippi", "mis*is*p*."));
        Assertions.assertFalse(solu.isMatch("aa", "a"));
        Assertions.assertTrue(solu.isMatch("aa", "a*"));
        Assertions.assertTrue(solu.isMatch("ab", ".*"));
    }
}
