package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class lc336_Palindrome_Pairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        Set<List<Integer>> ans = new HashSet<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (String w : words) {
            char[] arr = w.toCharArray();
            int N = arr.length;
            reverse(arr);
            String reverseWord = new String(arr);

            for (int i = N; i >= 0; i--) {
                String candi = reverseWord.substring(0, i);

                if (candi.equals(w) || !map.containsKey(candi))
                    continue;

                if (isPalindrome(candi + w)) {
                    List<Integer> found = List.of(map.get(candi), map.get(w));

                    if (!ans.contains(found))
                        ans.add(found);
                }
            }

            for (int i = 0; i <= N; i++) {
                String candi = reverseWord.substring(i, N);

                if (candi.equals(w) || !map.containsKey(candi))
                    continue;

                if (isPalindrome(w + candi)) {
                    List<Integer> found = List.of(map.get(w), map.get(candi));

                    if (!ans.contains(found))
                        ans.add(found);
                }
            }
        }

        return new ArrayList<>(ans);
    }

    public List<List<Integer>> palindromePairs01(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Set<Integer> set = new TreeSet<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
            set.add(words[i].length());
        }

        for (int i = 0; i < words.length; i++) {
            char[] arr = words[i].toCharArray();
            reverse(arr);
            String a = String.valueOf(arr);
            int n = a.length();

            if (map.containsKey(a) && map.get(a) != i) // abcd dcba
                ans.add(List.of(i, map.get(a)));

            for (Integer l : set) {
                if (l == n) break; // only iterate strings with smaller # of characters

                // abcd cba
                if (isPalindrome(a, 0, n - 1 - l)) {
                    String b = a.substring(n - l);
                    if (map.containsKey(b)) ans.add(List.of(i, map.get(b)));
                }

                // dcb abcd
                if (isPalindrome(a, l, n - 1)) {
                    String b = a.substring(0, l);
                    if (map.containsKey(b)) ans.add(List.of(map.get(b), i));
                }
            }
        }

        return ans;
    }

    private boolean isPalindrome(String w) {
        return isPalindrome(w, 0, w.length()-1);
    }

    private boolean isPalindrome(String w, int lo, int hi) {
        while (lo < hi) {
            if (w.charAt(lo++) != w.charAt(hi--))
                return false;
        }

        return true;
    }

    private void reverse(char[] arr) {
        int lo = 0;
        int hi = arr.length-1;

        while (lo < hi) {
            swap(arr, lo++, hi--);
        }
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void test01() {
        lc336_Palindrome_Pairs solu = new lc336_Palindrome_Pairs();

        List<List<Integer>> res = solu.palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"});
        Set<List<Integer>> set = new HashSet<>(res);

        Assertions.assertEquals(4, res.size());
        Assertions.assertTrue(set.contains(List.of(0,1)));
        Assertions.assertTrue(set.contains(List.of(1,0)));
        Assertions.assertTrue(set.contains(List.of(3,2)));
        Assertions.assertTrue(set.contains(List.of(2,4)));
    }

    @Test
    public void test11() {
        lc336_Palindrome_Pairs solu = new lc336_Palindrome_Pairs();

        List<List<Integer>> res = solu.palindromePairs(new String[]{"abcd","cba"});
        Set<List<Integer>> set = new HashSet<>(res);

        Assertions.assertEquals(1, res.size());
        Assertions.assertTrue(set.contains(List.of(0,1)));
    }

    @Test
    public void test02() {
        lc336_Palindrome_Pairs solu = new lc336_Palindrome_Pairs();

        List<List<Integer>> res = solu.palindromePairs(new String[]{"bat","tab","cat"});
        Set<List<Integer>> set = new HashSet<>(res);

        Assertions.assertEquals(2, res.size());
        Assertions.assertTrue(set.contains(List.of(0,1)));
        Assertions.assertTrue(set.contains(List.of(1,0)));
    }

    @Test
    public void test03() {
        lc336_Palindrome_Pairs solu = new lc336_Palindrome_Pairs();

        List<List<Integer>> res = solu.palindromePairs(new String[]{"a",""});
        Set<List<Integer>> set = new HashSet<>(res);

        Assertions.assertEquals(2, res.size());
        Assertions.assertTrue(set.contains(List.of(0,1)));
        Assertions.assertTrue(set.contains(List.of(1,0)));
    }

    @Test
    public void test04() {
        lc336_Palindrome_Pairs solu = new lc336_Palindrome_Pairs();

        List<List<Integer>> res = solu.palindromePairs(new String[]{"a","b","c","ab","ac","aa"});
        Set<List<Integer>> set = new HashSet<>(res);

        Assertions.assertEquals(6, res.size());
        Assertions.assertTrue(set.contains(List.of(3,0)));
        Assertions.assertTrue(set.contains(List.of(1,3)));
        Assertions.assertTrue(set.contains(List.of(4,0)));
        Assertions.assertTrue(set.contains(List.of(2,4)));
        Assertions.assertTrue(set.contains(List.of(5,0)));
        Assertions.assertTrue(set.contains(List.of(0,5)));
    }

    @Test
    public void test05() {
        lc336_Palindrome_Pairs solu = new lc336_Palindrome_Pairs();

        List<List<Integer>> res = solu.palindromePairs(new String[]{"a","abc","aba",""});
        Set<List<Integer>> set = new HashSet<>(res);

        Assertions.assertEquals(4, res.size());
        Assertions.assertTrue(set.contains(List.of(0,3)));
        Assertions.assertTrue(set.contains(List.of(3,0)));
        Assertions.assertTrue(set.contains(List.of(2,3)));
        Assertions.assertTrue(set.contains(List.of(3,2)));
    }
}
