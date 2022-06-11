package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class lc678_Valid_Parenthesis_String {
    public boolean checkValidString(String s) {
        char[] arr = s.toCharArray();

        return visit(arr);
    }

    private boolean visit(char[] arr) {
//        int low = 0, high = 0;
//        for (char c : arr) {
//            if (c == '(') {
//                ++low; ++high;
//            } else if (c == ')') {
//                if (low > 0) --low;
//                --high;
//            } else {
//                if (low > 0) --low;
//                ++high;
//            }
//            if (high < 0) return false;
//        }
//        return low == 0;

        int left = 0, right = 0, n = arr.length;

        for (int i = 0; i < n; ++i) {
            if (arr[i] == '(' || arr[i] == '*') ++left;
            else --left;
            if (left < 0) return false;
        }
        if (left == 0) return true;
        for (int i = n - 1; i >= 0; --i) {
            if (arr[i] == ')' || arr[i] == '*') ++right;
            else --right;
            if (right < 0) return false;
        }
        return true;
    }

    private boolean visit(char[] arr, int idx, int sum) {
        if (sum < 0)
            return false;

        if (idx == arr.length)
            return sum == 0;

        if (arr[idx] == '(')
            return visit(arr, idx+1, sum+1);

        if (arr[idx] == ')')
            return visit(arr, idx+1, sum-1);

        return visit(arr, idx+1, sum) // * -> ''
            || visit(arr, idx+1, sum+1) // * -> (
            || visit(arr, idx+1, sum-1); // * -> )
    }

    @Test
    public void test01() {
        lc678_Valid_Parenthesis_String solu = new lc678_Valid_Parenthesis_String();

        Assertions.assertTrue(solu.checkValidString("(()(())()())*((()(())))*()(*)()()(*((()((*(*))))()*()(()((()(*((()))*(((())(())))*))(()*))(()*)"));
        Assertions.assertTrue(solu.checkValidString("**************************************************))))))))))))))))))))))))))))))))))))))))))))))))))"));
        Assertions.assertTrue(solu.checkValidString("()"));
        Assertions.assertTrue(solu.checkValidString("(*)"));
        Assertions.assertTrue(solu.checkValidString("(*))"));
    }
}
