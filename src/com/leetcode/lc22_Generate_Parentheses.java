package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class lc22_Generate_Parentheses {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();

        visit(n, 0, 0, "", ans);
        return ans;
    }

    private void visit(int n, int left, int right, String path, List<String> ans) {
        if (left + right == 2*n) {
            ans.add(path);
            return;
        }

        if (left < n)
            visit(n, left+1, right, path+"(", ans);

        if (right < left)
            visit(n, left, right+1, path+")", ans);
    }

    @Test
    public void testParenthesis_3() {
        lc22_Generate_Parentheses solu = new lc22_Generate_Parentheses();
        Set<String> set = new HashSet<>(solu.generateParenthesis(3));

        Assertions.assertEquals(5, set.size());
        Assertions.assertTrue(set.contains("((()))"));
        Assertions.assertTrue(set.contains("(()())"));
        Assertions.assertTrue(set.contains("(())()"));
        Assertions.assertTrue(set.contains("()(())"));
        Assertions.assertTrue(set.contains("()()()"));
    }
}
