package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class lc214_Shortest_Palindrome {
    public String shortestPalindrome(String s) {
        char[] arr = s.toCharArray();

        reverse(arr);
        return manacher01(arr);
    }

    private void reverse(char[] arr) {
        int lo = 0;
        int hi = arr.length-1;

        while (lo < hi) {
            swap(arr, lo++, hi--);
        }
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private String manacher01(char[] arr) {
        char[] str = manacherString(arr);
        int N = str.length;
        int[] pArr = new int[N];
        int C = -1;
        int R = -1;
        int pos = 0;

        // find first Palindrome covers last char in pArr by manacher algo.
        for (int i = 0; i < N; i++) {
            pArr[i] = R > i ? Math.min(pArr[2*C-i], R-i) : 1;
            while (i + pArr[i] < N && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else
                    break;
            }

            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }

            if (i + pArr[i] >= N) {
                pos = i - pArr[i];
                break;
            }
        }

        char[] buf = String.valueOf(arr, 0, (pos+1)/2).toCharArray();
        reverse(buf);

        return String.valueOf(arr) + String.valueOf(buf);
    }

    private char[] manacherString(char[] arr) {
        int len = arr.length;
        char[] ret = new char[len*2+1];

        Arrays.fill(ret, '#');
        for (int i = 0; i < len; i++) {
            ret[i*2+1] = arr[i];
        }

        return ret;
    }

    @Test
    public void test01() {
        lc214_Shortest_Palindrome solu = new lc214_Shortest_Palindrome();

        Assertions.assertEquals("aaacecaaa", solu.shortestPalindrome("aacecaaa"));
        Assertions.assertEquals("dcbabcd", solu.shortestPalindrome("abcd"));
    }
}
