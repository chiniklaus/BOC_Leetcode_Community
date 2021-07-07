// 289. Game of Life

// simple mutation
// to do this in O(1) space, we need some kind of indicator to tell the cell was dead. my solution was to add 10 to the
// neighbor live cell count.

// O(m*n) space
class Solution {
    int row;
    int col;
    public void gameOfLife(int[][] board) {
        row = board.length;
        col = board[0].length;
        int[][] newboard = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                spread(board, newboard, i, j);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 1) {
                    int nei = newboard[i][j];
                    if (nei < 2 || nei > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    if (newboard[i][j] == 3) board[i][j] = 1;
                }
            }
        }
    }
    private void spread(int[][] board, int[][] newboard, int i, int j) {
        int cnt = 0;
        if (i != 0 && board[i-1][j] == 1) cnt++;
        if (i != row-1 && board[i+1][j] == 1) cnt++;
        if (j != 0 && board[i][j-1] == 1) cnt++;
        if (j != col-1 && board[i][j+1] == 1) cnt++;
        if (i != 0 && j != 0 && board[i-1][j-1] == 1) cnt++;
        if (i != 0 && j != col-1 && board[i-1][j+1] == 1) cnt++;
        if (i != row-1 && j != 0 && board[i+1][j-1] == 1) cnt++;
        if (i != row-1 && j != col-1 && board[i+1][j+1] == 1) cnt++;
        newboard[i][j] = cnt;
    }
}

// O(1) space
class Solution {
    int row;
    int col;
    public void gameOfLife(int[][] board) {
        row = board.length;
        col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                spread(board, i, j);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean live = true;
                if (board[i][j] >= 10) live = false;
                if (live) {
                    int nei = board[i][j];
                    if (nei < 2 || nei > 3) {
                        board[i][j] = 0;
                    } else {
                        board[i][j] = 1;
                    }
                } else {
                    if (board[i][j] - 10 == 3) {
                        board[i][j] = 1;
                    } else {
                        board[i][j] = 0;
                    }
                }
            }
        }
    }
    private void spread(int[][] board, int i, int j) {
        int cnt = 0;
        int status = 0;
        if (board[i][j] == 0) status = 10;
        if (i != 0 && board[i-1][j] < 10) cnt++;
        if (i != row-1 && board[i+1][j] == 1) cnt++;
        if (j != 0 && board[i][j-1] < 10) cnt++;
        if (j != col-1 && board[i][j+1] == 1) cnt++;
        if (i != 0 && j != 0 && board[i-1][j-1] < 10) cnt++;
        if (i != 0 && j != col-1 && board[i-1][j+1] < 10) cnt++;
        if (i != row-1 && j != 0 && board[i+1][j-1] == 1) cnt++;
        if (i != row-1 && j != col-1 && board[i+1][j+1] == 1) cnt++;
        board[i][j] = cnt + status;
    }
}