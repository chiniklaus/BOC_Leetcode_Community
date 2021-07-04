// 253. Meeting Rooms II

// we do a simulation, put on going meetings in a heap. for each new meeting, we pop the meetings in
// the heap if that meeting is over. then we put the new meeting in the heap. keep track of the
// maximum size of the heap

// time O(nlogn), space O(n). can also just put the end time of the meeting in heap since that is all
// we care about.

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int max = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        Arrays.sort(intervals, (a,b) -> a[0]-b[0]);
        for (int i = 0; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (heap.size() == 0) {
                heap.offer(curr[1]);
                max = Math.max(max, 1);
            } else {
                while(heap.size() != 0 && heap.peek() <= curr[0]) heap.poll();
                heap.offer(curr[1]);
                max = Math.max(max, heap.size());
            }
        }
        return max;
    }
}