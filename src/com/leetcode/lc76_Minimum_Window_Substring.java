package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc76_Minimum_Window_Substring {
    public String minWindow(String s, String t) {
        int[] map = new int[256];
        int total = 0;

        for (char c : t.toCharArray()) {
            map[(int)c]++;
            total++;
        }

        int start = 0;
        int minSize = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;
        char[] buf = s.toCharArray();
        while (r < buf.length) {
            char c = buf[r++];

            map[(int)c]--;
            if (map[(int)c] >= 0) {
                total--;
            }

            if (total == 0) {
                while (l < r) {
                    c = buf[l++];
                    map[(int)c]++;
                    if (map[(int)c] > 0) {
                        total++;
                        if (r - l + 1 < minSize) {
                            minSize = r-l+1;
                            start = l-1;
                        }
                        break;
                    }
                }
            }
        }

        if (minSize == Integer.MAX_VALUE)
            return "";
        return s.substring(start, start+minSize);
    }

    @Test
    public void test01() {
        lc76_Minimum_Window_Substring solu = new lc76_Minimum_Window_Substring();

        Assertions.assertEquals("BANC", solu.minWindow("ADOBECODEBANC", "ABC"));
        Assertions.assertEquals("a", solu.minWindow("a", "a"));
        Assertions.assertEquals("", solu.minWindow("a", "aa"));
    }
}
