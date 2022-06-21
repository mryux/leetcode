package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class lc17_Letter_Combinations_of_a_Phone_Number {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();

        if (digits == null || digits.length() == 0)
            return ans;

        String[] dial = new String[] {
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };

        char[] buf = new char[digits.length()];

        visit(digits.toCharArray(), 0, dial, buf, ans);

        return ans;
    }

    private void visit(char[] arr, int idx, String[] dial, char[] buf, List<String> list) {
        if (idx == buf.length) {
            list.add(String.valueOf(buf));
            return;
        }

        char d = arr[idx];
        for (char c : dial[d-'2'].toCharArray()) {
            buf[idx] = c;
            visit(arr, idx+1, dial, buf, list);
        }
    }

    @Test
    public void test01() {
        lc17_Letter_Combinations_of_a_Phone_Number solu = new lc17_Letter_Combinations_of_a_Phone_Number();

        List<String> ans = solu.letterCombinations("23");

        Assertions.assertTrue(ans.contains("ad"));
        Assertions.assertTrue(ans.contains("ae"));
        Assertions.assertTrue(ans.contains("af"));
        Assertions.assertTrue(ans.contains("bd"));
        Assertions.assertTrue(ans.contains("be"));
        Assertions.assertTrue(ans.contains("bf"));
        Assertions.assertTrue(ans.contains("cd"));
        Assertions.assertTrue(ans.contains("ce"));
        Assertions.assertTrue(ans.contains("cf"));
    }
}
