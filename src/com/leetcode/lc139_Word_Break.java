package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class lc139_Word_Break {
    public boolean wordBreak(String s, List<String> wordDict) {
        TrieNode root = buildTrieNode(wordDict);

        char[] arr = s.toCharArray();
        int N = arr.length;
        boolean[] dp = new boolean[N+1];

        dp[N] = true;
        for (int i = N-1; i >= 0; i--) {
            TrieNode node = root;

            for (int end = i; end < N; end++) {
                int idx = arr[end]-'a';

                if (node.next[idx] == null)
                    break;

                node = node.next[idx];
                if (node.isWord && dp[end+1]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[0];
    }

    private boolean visit(char[] arr, int start, TrieNode root) {
        if (start >= arr.length)
            return true;

        TrieNode node = root;

        for (int i = start; i < arr.length; i++) {
            int idx = arr[i]-'a';

            if (node.next[idx] == null)
                return false;

            node = node.next[idx];
            if (node.isWord) {
                if (visit(arr, i+1, root))
                    return true;
            }
        }

        return false;
    }

    private TrieNode buildTrieNode(List<String> wordDict) {
        TrieNode root = new TrieNode();

        for (String word : wordDict) {
            char[] arr = word.toCharArray();
            TrieNode node = root;

            for (char c : arr) {
                int idx = c-'a';

                if (node.next[idx] == null) {
                    node.next[idx] = new TrieNode();
                }
                node = node.next[idx];
            }
            node.isWord = true;
        }

        return root;
    }

    @Test
    public void test01() {
        lc139_Word_Break solu = new lc139_Word_Break();

        Assertions.assertTrue(solu.wordBreak("leetcode", List.of("leet", "code")));
        Assertions.assertTrue(solu.wordBreak("applepenapple", List.of("apple", "pen")));
        Assertions.assertFalse(solu.wordBreak("catsandog", List.of("cats","dog","sand","and","cat")));
    }
}
