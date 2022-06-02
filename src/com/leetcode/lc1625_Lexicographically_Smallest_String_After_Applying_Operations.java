package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class lc1625_Lexicographically_Smallest_String_After_Applying_Operations {
    public String findLexSmallestString(String s, int a, int b) {
        ans = "a";
        visit(s, a, b, new HashSet<>());

        return ans;
    }

    private String ans = "a";

    public void visit(String s, int a, int b, Set<String> visited) {
        if (visited.contains(s))
            return;

        visited.add(s);
        if (s.compareTo(ans) < 0)
            ans = s;

        visit(add(s, a), a, b, visited);
        visit(shift(s, b), a, b, visited);
    }

    private String add(String s, int a) {
        char[] buf = s.toCharArray();

        for (int i = 1; i < buf.length; i+=2) {
            buf[i] = (char)((buf[i]-'0'+a) % 10 + '0');
        }

        return new String(buf);
    }

    private String shift(String s, int b) {
        int len = s.length();

        return s.substring(len-b, len) + s.substring(0, len-b);
    }

    @Test
    public void test01() {
        lc1625_Lexicographically_Smallest_String_After_Applying_Operations solu = new lc1625_Lexicographically_Smallest_String_After_Applying_Operations();

        Assertions.assertEquals("2050", solu.findLexSmallestString("5525", 9, 2));
        Assertions.assertEquals("24", solu.findLexSmallestString("74", 5, 1));
        Assertions.assertEquals("0011", solu.findLexSmallestString("0011", 4, 2));
    }
}
