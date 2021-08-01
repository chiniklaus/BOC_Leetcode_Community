// 827. Making A Large Island

// We can first find all the island and give them an index, use a map to store the index
// islands' size. After that, we go through the entire grid to find 0s. For each 0, combine
// the islands of its four directions to find the largest combined island.

// Time O(row * col), space O(row * col)
class Solution {
    int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public int largestIsland(int[][] grid) {
        Map<Integer,Integer> map = new HashMap<>();
        int max = 0;
        int islandIndex = 2;
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int size = findIslands(i,j,grid,islandIndex);
                    map.put(islandIndex,size);
                    max = Math.max(max, size);
                    islandIndex++;
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    int local = 1;
                    Set<Integer> seen = new HashSet<>();
                    for (int[] d : dir) {
                        int ni = i + d[0];
                        int nj = j + d[1];
                        if (ni >= 0 && ni < row && nj >= 0 && nj < col && grid[ni][nj] != 0 && !seen.contains(grid[ni][nj])) {
                            local += map.get(grid[ni][nj]);
                            seen.add(grid[ni][nj]);
                        }
                    }
                    max = Math.max(max, local);
                }
            }
        }
        return max;
    }
    private int findIslands(int i, int j, int[][] grid, int index) {
        grid[i][j] = index;
        int local = 1;
        for (int[] d : dir) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length && grid[ni][nj] == 1) {
                local += findIslands(ni,nj,grid,index);
            }
        }
        return local;
    }
}