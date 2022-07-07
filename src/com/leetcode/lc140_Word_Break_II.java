package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class lc140_Word_Break_II {
    public List<String> wordBreak(String s, List<String> wordDict) {
        TrieNode root = buildTrieNode(wordDict);

        char[] arr = s.toCharArray();
        int N = arr.length;
        int[] dp = new int[N+1];
        Map<Integer, List<String>> map = new HashMap<>();

        dp[N] = 1;
        map.put(N, List.of(""));
        for (int i = N-1; i >= 0; i--) {
            TrieNode node = root;
            List<String> list = new ArrayList<>();

            for (int end = i; end < N; end++) {
                int idx = arr[end]-'a';

                if (node.next[idx] == null)
                    break;

                node = node.next[idx];
                if (node.isWord && dp[end+1] > 0) {
                    dp[i] += dp[end+1];
                    String curr = String.valueOf(arr, i, end-i+1);

                    for (String child : map.get(end+1)) {
                        if (child.length() > 0)
                            list.add(curr + " " + child);
                        else
                            list.add(curr);
                    }
                }
            }
            map.put(i, list);
        }

        return map.get(0);
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
        lc140_Word_Break_II solu = new lc140_Word_Break_II();

        List<String> ans = solu.wordBreak("catsanddog", List.of("cat","cats","and","sand","dog"));

        Assertions.assertEquals(2, ans.size());
        Assertions.assertTrue(ans.contains("cats and dog"));
        Assertions.assertTrue(ans.contains("cat sand dog"));

        ans = solu.wordBreak("pineapplepenapple", List.of("apple","pen","applepen","pine","pineapple"));
        Assertions.assertEquals(3, ans.size());
        Assertions.assertTrue(ans.contains("pine apple pen apple"));
        Assertions.assertTrue(ans.contains("pineapple pen apple"));
        Assertions.assertTrue(ans.contains("pine applepen apple"));

        ans = solu.wordBreak("catsandog", List.of("cats","dog","sand","and","cat"));
        Assertions.assertEquals(0, ans.size());
    }
}
