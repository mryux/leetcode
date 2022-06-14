package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
by manacher algorithm
 */
public class lc5_Longest_Palindromic_Substring {
    public String longestPalindrome(String s) {
        return manacher01(s);
    }

    private String manacher01(String s) {
        char[] str = managerString(s);
        int[] pArr = new int[str.length];
        int C = -1;
        int R = -1;
        int max = 0;
        int pos = 0;

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2*C-i], R-i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else
                    break;
            }

            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }

            if (pArr[i] > max) {
                pos = i;
                max = pArr[i];
            }
        }

        // get result from pos and max length.
        char[] buf = new char[max-1];
        int c = 0;

        for (int i = pos - max + 1; i < pos + max; i++) {
            if (str[i] != '#') {
                buf[c++] = str[i];
            }
        }

        return String.valueOf(buf);
    }

    private char[] managerString(String s) {
        char[] ret = new char[s.length()*2+1];

        Arrays.fill(ret, '#');
        for (int i = 0; i < s.length(); i++) {
            ret[i*2+1] = s.charAt(i);
        }

        return ret;
    }

    @Test
    public void test01() {
        lc5_Longest_Palindromic_Substring solu = new lc5_Longest_Palindromic_Substring();

        Assertions.assertEquals("bab", solu.longestPalindrome("babad"));
        Assertions.assertEquals("bb", solu.longestPalindrome("cbbd"));
    }
}
