package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class lc44_Wildcard_Matching {
    public boolean isMatch(String s, String p) {
        char[] arr = s.toCharArray();
        char[] pattern = p.toCharArray();

        pattern = trimStar(pattern);
        return validateExpr(arr, pattern)
                && visit_dp(arr, pattern);
//            && visit(arr, 0, pattern, 0);
    }

    public boolean isMatch01(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;

        while (sIdx < sLen) {
            // If the pattern character = string character
            // or pattern character = '?'
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                ++sIdx;
                ++pIdx;

                // If pattern character = '*'
            } else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                // Check the situation
                // when '*' matches no characters
                starIdx = pIdx;
                sTmpIdx = sIdx;
                ++pIdx;

                // If pattern character != string character
                // or pattern is used up
                // and there was no '*' character in pattern
            } else if (starIdx == -1) {
                return false;

                // If pattern character != string character
                // or pattern is used up
                // and there was '*' character in pattern before
            } else {
                // Backtrack: check the situation
                // when '*' matches one more character
                pIdx = starIdx + 1;
                sIdx = sTmpIdx + 1;
                sTmpIdx = sIdx;
            }
        }

        // The remaining characters in the pattern should all be '*' characters
        for (int i = pIdx; i < pLen; i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

    private boolean validateExpr(char[] arr, char[] pattern) {
        for (char v : arr) {
            if (v == '*' || v == '?')
                return false;
        }

        return true;
    }

    private char[] trimStar(char[] pattern) {
        int hi = 0;
        List<Character> list = new ArrayList<>();

        while (hi < pattern.length) {
            if (pattern[hi] == '*') {
                int len = list.size();
                if (len == 0 || list.get(len-1) != '*') {
                    list.add(pattern[hi]);
                }
            }
            else {
                list.add(pattern[hi]);
            }
            hi++;
        }

        char[] ret = new char[list.size()];
        for (int i = 0; i < ret.length; i++)
            ret[i] = list.get(i);

        return ret;
    }

    private boolean visit_dp(char[] arr, char[] pattern) {
        int N = arr.length;
        int M = pattern.length;
        boolean[][] dp = new boolean[N+1][M+1];

        // init dp
        dp[N][M] = true;
        if (M > 0)
            dp[N][M-1] = pattern[M-1] == '*';
        if (N > 0 && M > 0)
            dp[N-1][M-1] = (arr[N-1] == pattern[M-1] || '.' == pattern[M-1] || pattern[M-1] == '*');

        for (int i = N-1; i >= 0; i--) {
            for (int j = M-1; j >= 0; j--) {
                if (pattern[j] != '*') {
                    dp[i][j] = (arr[i] == pattern[j] || pattern[j] == '?') && dp[i+1][j+1];
                }
                else {
                    int si = i;
                    while (si <= N) {
                        if (dp[si][j+1]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                }
            }
        }

        return dp[0][0];
    }

    private boolean visit(char[] arr, int si, char[] pattern, int pi) {
        if (pi == pattern.length)
            return si == arr.length;

        if (pattern[pi] != '*') {
            return si < arr.length
                    && (arr[si] == pattern[pi] || pattern[pi] == '?')
                    && visit(arr, si+1, pattern, pi+1);
        }

        // patter[pi] == '*'
        while (si <= arr.length) {
            if (visit(arr, si, pattern, pi+1))
                return true;
            si++;
        }

        return false;
    }

    @Test
    public void test01() {
        lc44_Wildcard_Matching solu = new lc44_Wildcard_Matching();

        Assertions.assertTrue(solu.isMatch("", ""));
        Assertions.assertFalse(solu.isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb", "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
        Assertions.assertTrue(solu.isMatch("", "******"));
        Assertions.assertTrue(solu.isMatch("aa", "*"));
        Assertions.assertFalse(solu.isMatch("bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab", "b*b*ab**ba*b**b***bba"));
        Assertions.assertFalse(solu.isMatch("aa", "a"));
        Assertions.assertFalse(solu.isMatch("cb", "?a"));
    }
}
