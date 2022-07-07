package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class lc127_Word_Ladder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;

        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> beginSet = new HashSet<>(List.of(beginWord));
        Set<String> endSet = new HashSet<>(List.of(endWord));

        int steps = 2;

        while (!beginSet.isEmpty()) {
            Set<String> nextSet = new HashSet<>();

            for (String word : beginSet) {
                char[] arr = word.toCharArray();

                for (int i = 0; i < arr.length; i++) {
                    char tmp = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == tmp)
                            continue;

                        arr[i] = c;
                        String w = String.valueOf(arr);
                        if (endSet.contains(w)) {
                            return steps;
                        }
                        if (wordSet.contains(w)) {
                            wordSet.remove(w); // need this to stop endless-loop.
                            nextSet.add(w);
                        }
                    }
                    arr[i] = tmp;
                }
            }

            beginSet = nextSet;
            if (nextSet.size() > endSet.size()) {
                beginSet = endSet;
                endSet = nextSet;
            }
            steps++;
        }

        return 0;
    }

    @Test
    public void test01() {
        lc127_Word_Ladder solu = new lc127_Word_Ladder();

        Assertions.assertEquals(5, solu.ladderLength("hit", "cog", List.of("hot","dot","dog","lot","log","cog")));
        Assertions.assertEquals(0, solu.ladderLength("hit", "cog", List.of("hot","dot","dog","lot","log")));
    }
}
