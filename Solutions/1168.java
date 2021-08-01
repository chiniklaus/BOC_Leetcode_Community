// 1168. Optimize Water Distribution in a Village

// Kruskal's Algorithm to build the minimum spanning tree
class Solution {
    int[] parents;
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> a[2] == b[2] ? Math.abs(a[0]-a[1]) - Math.abs(b[0]-b[1]) : a[2]-b[2]);
        for (int[] p : pipes)  heap.offer(p);
        for (int i = 1; i <= n; i++) heap.offer(new int[]{i,i,wells[i-1]});
        int total = 0;
        parents = new int[n+1];
        boolean[] seen = new boolean[n+1];
        for (int i = 1; i <= n; i++) parents[i] = i;
        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int from = curr[0];
            int to = curr[1];
            int cost = curr[2];
            if (from == to && !seen[find(from)]) {
                seen[from] = true;
                seen[find(from)] = true;
                total += cost;
            } else if (find(from) != find(to)) {
                int pfrom = find(from);
                int pto = find(to);
                if (seen[pfrom] && seen[pto]) continue;
                union(from, to);
                total += cost;
                if (seen[pfrom] || seen[pto]) {
                    seen[from] = true;
                    seen[to] = true;
                    seen[pfrom] = true;
                    seen[pto] = true;
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int pi = find(i);
            if (!map.containsKey(pi)) map.put(pi,i);
            if (wells[i-1] < wells[map.get(pi)-1]) map.put(pi,i);
            if (seen[i]) seen[pi] = true;
        }
        for (Map.Entry<Integer,Integer> e : map.entrySet()) {
            if (!seen[e.getKey()]) total += wells[e.getValue()-1];
        }
        return total;
    }
    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        parents[px] = py;
    }
    private int find(int x) {
        int px = parents[x];
        if (px != x) parents[x] = find(px);
        return parents[x];
    }
}