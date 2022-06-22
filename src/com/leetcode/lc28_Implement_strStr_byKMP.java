package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc28_Implement_strStr_byKMP {
    public int strStr(String s, String m) {
        if (s == null || m == null || s.length() == 0 || m.length() == 0)
            return -1;

        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int x = 0;  // current position in s
        int y = 0;  // current position in match
        int[] next = getNextArray(match);

        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }

        return y == match.length ? x - y : -1;
    }

    private int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[] {-1};
        }

        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;

        int i = 2;
        int cn = 0;

        while (i < next.length) {
            if (match[i-1] == match[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                i++;
            }
        }

        return next;
    }

    @Test
    public void test01() {
        lc28_Implement_strStr_byKMP solu = new lc28_Implement_strStr_byKMP();

        Assertions.assertEquals(2, solu.strStr("hello", "ll"));
        Assertions.assertEquals(-1, solu.strStr("aaaaaa", "bba"));
    }
}
