package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc38_Count_and_Say {
    public String countAndSay(int n) {
        StringBuilder builder = new StringBuilder();
        String ans = "1";

        for (int i = 1; i < n; i++) {
            int count = 1;
            char c = ans.charAt(0);

            builder.setLength(0);
            for (int idx = 1; idx < ans.length(); idx++) {
                if (ans.charAt(idx) != ans.charAt(idx-1)) {
                    builder.append(count).append(c);

                    count = 1;
                    c = ans.charAt(idx);
                } else {
                    count++;
                }
            }
            builder.append(count).append(c);
            ans = builder.toString();
        }

        return ans;
    }

    @Test
    public void test01() {
        lc38_Count_and_Say solu = new lc38_Count_and_Say();

        Assertions.assertEquals("1", solu.countAndSay(1));
        Assertions.assertEquals("1211", solu.countAndSay(4));
    }
}
