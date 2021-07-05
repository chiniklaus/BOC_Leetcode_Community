// 882. Reachable Nodes In Subdivided Graph

// we use dijkstra on this one. but be aware, we still need to handle the edge case where we have
// seen a node but need to exam the path to that node

class Solution {
    public int reachableNodes(int[][] edges, int M, int N) {
        // three steps for dijkstra
        // find the node with smallest dist to origin
        // mark that node as seen
        // upadte that node's unseen neighbors
        int[][] graph = new int[N][N];
        for (int[] connection : graph) Arrays.fill(connection, -1);
        for (int [] edge : edges) {
            int first = edge[0];
            int second = edge[1];
            int nodes = edge[2];
            graph[first][second] = nodes;
            graph[second][first] = nodes;
        }
        //          index dist
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        boolean[] seen = new boolean[N];
        int cnt = 0;
        heap.offer(new int[]{0,0});
        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int index = curr[0];
            int steps = curr[1];
            if (seen[index]) continue;
            seen[index] = true;
            cnt++;
            int[] neighbors = graph[index];
            for (int i = 0; i < N; i++) {
                if (neighbors[i] != -1) {
                    int between = neighbors[i];
                    int steps_left = M - steps;
                    if (steps_left <= between) {
                        cnt += steps_left;
                        graph[i][index] = between - steps_left;
                    } else {
                        cnt += between;
                        graph[i][index] = 0;
                        if (!seen[i]) heap.offer(new int[]{i,steps+between+1});
                    }
                }
            }
        }
        return cnt;
    }
}