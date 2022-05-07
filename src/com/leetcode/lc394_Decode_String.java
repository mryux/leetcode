package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given an encoded string, return its decoded string.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 */
public class lc394_Decode_String {
    public String decodeString(String s) {
        char[] buf = s.toCharArray();
        StringBuilder builder = new StringBuilder();

        visit(buf, 0, builder);
        return builder.toString();
    }

    private int visit(char[] buf, int start, StringBuilder builder) {
        int times = 0;
        StringBuilder currBuilder = new StringBuilder();

        while (start < buf.length) {
            char curr = buf[start];

            if (isChar(curr)) {
                currBuilder.append(curr);
            } else if (isNumeric(curr)) {
                times = times * 10 + curr - '0';
            } else if (curr == '[') {
                StringBuilder childBuilder = new StringBuilder();

                start = visit(buf, start+1, childBuilder);
                if (times > 0) {
                    for (int i = 0; i < times; i++) {
                        currBuilder.append(childBuilder);
                    }
                    times = 0;
                }
            } else if (curr == ']') {
                break;
            }
            start++;
        }

        builder.append(currBuilder);
        return start;
    }

    private boolean isChar(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isNumeric(char c) {
        return c >= '0' && c <= '9';
    }

    @Test
    public void test01() {
        lc394_Decode_String solu = new lc394_Decode_String();

        Assertions.assertEquals("aaabcbc", solu.decodeString("3[a]2[bc]"));
        Assertions.assertEquals("accaccacc", solu.decodeString("3[a2[c]]"));
        Assertions.assertEquals("abcabccdcdcdef", solu.decodeString("2[abc]3[cd]ef"));
    }
}
