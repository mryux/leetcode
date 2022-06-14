package com.leetcode;

public class TrieNode {
    TrieNode[] next = new TrieNode[26];
    boolean isWord;

    public void add(String word) {
        char[] buf = word.toCharArray();

        TrieNode curr = this;
        for (char c : buf) {
            int idx = c-'a';

            if (curr.next[idx] == null) {
                curr.next[idx] = new TrieNode();
            }
            curr = curr.next[idx];
        }
        curr.isWord = true;
    }

    public int visit(String s, int start) {
        TrieNode curr = this;
        while (start < s.length()) {
            char c = s.charAt(start);
            int idx = c - 'a';

            if (curr.isWord)
                return start-1;

            if (curr.next[idx] == null)
                return start;

            curr = curr.next[idx];
            start++;
        }

        return start;
    }
}