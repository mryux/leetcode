package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc79_Word_Search {
    public boolean exist(char[][] board, String word) {
        char[] arr = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == arr[0]) {
                    if (visit(board, i, j, arr, 0))
                        return true;
                }
            }
        }

        return false;
    }

    private boolean visit(char[][]board, int row, int col, char[] word, int idx) {
        // out of bound of board
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
            return false;
        // cell visited || word mismatch
        if (board[row][col] != word[idx])
            return false;

        if (idx == word.length-1)
            return true;

        char c = board[row][col];

        board[row][col] = 0;
        boolean found = visit(board, row-1, col+0, word, idx+1)
                || visit(board, row+1, col+0, word, idx+1)
                || visit(board, row, col-1, word, idx+1)
                || visit(board, row, col+1, word, idx+1);
        board[row][col] = c;

        return found;
    }

    @Test
    public void test01() {
        lc79_Word_Search solu = new lc79_Word_Search();

        Assertions.assertTrue(solu.exist(new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        }, "ABCCED"));
        Assertions.assertTrue(solu.exist(new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        }, "SEE"));
        Assertions.assertFalse(solu.exist(new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        }, "ABCB"));
    }
}
