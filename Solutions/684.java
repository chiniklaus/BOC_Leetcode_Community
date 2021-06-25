// 684. Redundant Connection

// union find

class Solution {
    int[] parents;
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parents = new int[n+1];
        for (int i = 1; i < n+1; i++) parents[i] = i;
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (find(from) == find(to)) return edge;
            union(from, to);
        }
        return new int[2];
    }
    private int find(int n) {
        int pa = parents[n];
        if (pa != n) {
            parents[n] = find(pa);
        }
        return parents[n];
    }
    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        parents[pa] = pb;
    }
}