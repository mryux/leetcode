package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class lc3_Longest_Substring_Without_Repeating_Characters {
    public int lengthOfLongestSubstring(String s) {
        return implementByDp(s.toCharArray());
    }

    private int implementByDp(char[] arr) {
        int[] map = new int[256];
        int pre = -1;
        int ans = 0;

        Arrays.fill(map, -1);
        for (int i = 0; i < arr.length; i++) {
            pre = Math.max(pre, map[arr[i]]);
            ans = Math.max(ans, i-pre);
            map[arr[i]] = i;
        }

        return ans;
    }

    /*
    implement with slide window.
     */
    private int implementBySlidewindow(String s) {
        int start = 0;
        int end = 0;
        char[] buf = s.toCharArray();
        char[] map = new char[256];
        int max = 0;

        while (start < buf.length) {
            char c = buf[start];

            map[c]++;
            if (map[c] > 1) {
                while (end < start) {
                    char rc = buf[end++];

                    map[rc]--;
                    if (map[rc] > 0) {
                        break;
                    }
                }
            }

            start++;
            max = Math.max(max, start-end);
        }

        return max;
    }

    @Test
    public void test01() {
        lc3_Longest_Substring_Without_Repeating_Characters solu = new lc3_Longest_Substring_Without_Repeating_Characters();

        Assertions.assertEquals(3, solu.lengthOfLongestSubstring("abcabcbb"));
        Assertions.assertEquals(1, solu.lengthOfLongestSubstring("bbbbb"));
        Assertions.assertEquals(3, solu.lengthOfLongestSubstring("pwwkew"));
    }
}
