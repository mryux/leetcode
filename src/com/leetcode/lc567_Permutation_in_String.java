package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc567_Permutation_in_String {
    public boolean checkInclusion(String s1, String s2) {
        int total = s1.length();
        int[] map = new int[256];
        for (char c : s1.toCharArray()) {
            map[(int)c]++;
        }

        int len = total;
        int l = 0;
        int r = 0;
        char[] buf = s2.toCharArray();

        while (r < buf.length) {
            char c = buf[r++];

            map[(int)c]--;
            if (map[(int)c] >= 0)
                total--;

            if (total == 0) {
                while (l < r) {
                    c = buf[l++];
                    map[(int)c]++;
                    if (map[(int)c] > 0) {
                        total++;
                        if (r - l + 1 == len)
                            return true;

                        break;
                    }
                }
            }
        }

        return false;
    }

    @Test
    public void test01() {
        lc567_Permutation_in_String solu = new lc567_Permutation_in_String();

        Assertions.assertTrue(solu.checkInclusion("ab", "eidbaooo"));
        Assertions.assertFalse(solu.checkInclusion("ab", "eidboaoo"));
    }
}
