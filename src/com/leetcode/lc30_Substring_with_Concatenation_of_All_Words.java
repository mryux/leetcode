package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class lc30_Substring_with_Concatenation_of_All_Words {
    public List<Integer> findSubstring(String s, String[] words) {
        return visit01(s, words);
    }

    private List<Integer> visit01(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        char[] arr = s.toCharArray();
        int wordLen = words[0].length();
        int allWordsLen = words.length * wordLen;
        for (int i = 0; i <= arr.length - allWordsLen; i++) {
            Map<String, Integer> found = new HashMap<>();

            int j = 0;
            for (j = 0; j < allWordsLen; j += wordLen) {
                String w = String.copyValueOf(arr, i+j, wordLen);

                if (!map.containsKey(w))
                    break;

                int count = found.getOrDefault(w, 0)+1;
                if (count > map.get(w))
                    break;

                found.put(w, count);
            }
            if (j == allWordsLen)
                ans.add(i);
        }

        return ans;
    }

    public List<Integer> findSubstring1(String s, String[] words) {
        int wordLength = words[0].length();
        int windowSize = words.length * wordLength;

        if (s.length() < windowSize) {
            return Collections.emptyList();
        }

        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word)) {
                map.put(word, map.size());
            }
        }
        int[] wordCounts = new int[map.size()];
        for (String word : words) {
            wordCounts[map.get(word)] += 1;
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < wordLength; i++) {
            int left = i;
            while (left + windowSize <= s.length()) {
                int[] usedWordsCounts = new int[wordCounts.length];
                int j = words.length;
                while (j > 0) {
                    String word = s.substring(left + (j - 1) * wordLength, left + j * wordLength);
                    Integer wordIndex = map.get(word);
                    if (wordIndex == null || ++usedWordsCounts[wordIndex] > wordCounts[wordIndex])
                        break;
                    j--;
                }

                if (j == 0) {
                    ans.add(left);
                }
                left += wordLength * Math.max(j, 1);
            }
        }
        return ans;
    }

    // ------------ sliding window implementation ------------
    private HashMap<String, Integer> wordCount = new HashMap<>();
    private int n;
    private int wordLength;
    private int substringSize;
    private int k;

    private void slidingWindow(int left, String s, List<Integer> answer) {
        HashMap<String, Integer> wordsFound = new HashMap<>();
        int wordsUsed = 0;
        boolean excessWord = false;
        for (int right = left; right <= n - wordLength; right += wordLength) {
            String sub = s.substring(right, right + wordLength);
            if (!wordCount.containsKey(sub)) {
                wordsFound.clear();
                wordsUsed = 0;
                excessWord = false;
                left = right + wordLength;
            } else {
                while (right - left == substringSize || excessWord) {
                    String leftmostWord = s.substring(left, left + wordLength);
                    left += wordLength;
                    wordsFound.put(leftmostWord, wordsFound.get(leftmostWord) - 1);
                    if (wordsFound.get(leftmostWord) >= wordCount.get(leftmostWord)) {
                        excessWord = false;
                    } else {
                        wordsUsed--;
                    }
                }
                wordsFound.put(sub, wordsFound.getOrDefault(sub, 0) + 1);
                if (wordsFound.get(sub) <= wordCount.get(sub)) {
                    wordsUsed++;
                } else {
                    excessWord = true;
                }
                if (wordsUsed == k && !excessWord) {
                    answer.add(left);
                }
            }
        }
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        n = s.length();
        k = words.length;
        wordLength = words[0].length();
        substringSize = wordLength * k;
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < wordLength; i++) {
            slidingWindow(i, s, answer);
        }
        return answer;
    }

    @Test
    public void test01() {
        lc30_Substring_with_Concatenation_of_All_Words solu = new lc30_Substring_with_Concatenation_of_All_Words();

        Assertions.assertArrayEquals(new int[]{0,1,2,3,4,5,6,7,8,9,10}, Common.toArray(solu.findSubstring("aaaaaaaaaaaaaa", new String[]{"aa","aa"})));
        Assertions.assertArrayEquals(new int[]{13}, Common.toArray(solu.findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo","barr","wing","ding","wing"})));
        Assertions.assertArrayEquals(new int[]{8}, Common.toArray(solu.findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"})));
        Assertions.assertArrayEquals(new int[]{0, 9}, Common.toArray(solu.findSubstring("barfoothefoobarman", new String[]{"foo","bar"})));
        Assertions.assertArrayEquals(new int[]{}, Common.toArray(solu.findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"})));
        Assertions.assertArrayEquals(new int[]{6,9,12}, Common.toArray(solu.findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"})));
    }
}
