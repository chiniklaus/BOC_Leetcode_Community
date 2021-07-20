// 1135. Connecting Cities With Minimum Cost

// Build Minimum Spanning Tree using Kruskal's algorithm

// Sort all the edges
// Iterate over the edges, if one edge can connect two nodes that are not already connected,
// ass the edge to our MST

// Time O(MlogN), space O(N)
class Solution {
    int[] parents;
    public int minimumCost(int n, int[][] connections) {
        parents = new int[n+1];
        Arrays.sort(connections, (a,b) -> a[2]-b[2]);
        for (int i = 0; i < parents.length; i++) parents[i] = i;
        int head = connections[0][0];
        int total = 0;
        for (int[] edge : connections) {
            int from = edge[0];
            int to = edge[1];
            int pfrom = find(from);
            int pto = find(to);
            if (pfrom != pto) {
                union(from, to);
                total += edge[2];
            }
        }
        int phead = find(head);
        for (int i = 1; i <= n; i++) if (find(i) != phead) return -1;
        return total;
    }
    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        parents[py] = px;
    }
    private int find(int x) {
        int px = parents[x];
        if (px != x) parents[x] = find(px);
        return parents[x];
    }
}