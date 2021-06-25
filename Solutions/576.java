// 576. Out of Boundary Paths

// we solve this using dfs + memorization. basically we try everything, but there will
// be redundant work, because we can can to one point with x steps left, but there could
// be other routes lead us there as well. we want to eliminate that.


// tips on modulo,we want to eliminate all possible overflows. to avoid that, everytime we to
// a addition, we module.

class Solution {
    int M = 1000000007;
    int count = 0;
    int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] memo = new int[m][n][maxMove+1];
        return dfs(m,n,maxMove,startRow,startColumn,memo);
    }
    private int dfs(int m, int n, int move, int x, int y, int[][][] memo) {
        if (x == m || x == -1 || y == n || y == -1) {
            return 1;
        } else {
            if (move == 0) return 0;
            if (memo[x][y][move] != 0) return memo[x][y][move];
            int local = 0;
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                int min = Math.min(Math.min(Math.min(nx+1,ny+1), m-nx), n-ny);
                if (min > move) continue;
                local += dfs(m,n,move-1,nx,ny,memo);
                local = local % M;
            }
            memo[x][y][move] = local;
            return local;
        }
    }