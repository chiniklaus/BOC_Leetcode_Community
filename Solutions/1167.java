// 1167. Minimum Cost to Connect Sticks

// the greedy solution here is to always combine the smallest two sticks to
// avoid adding more to the cost. so we use min-heap

class Solution {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i : sticks) heap.offer(i);
        int cost = 0;
        while (heap.size() > 1) {
            int next = heap.poll() + heap.poll();
            cost += next;
            heap.offer(next);
        }
        return cost;
    }
}