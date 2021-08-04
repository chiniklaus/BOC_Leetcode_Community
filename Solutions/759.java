// 759. Employee Free Time

// The initial thought is, putting all the intervals together, sort them by starting time,
// and then do a grand merging for all the intervals. After that, we can just look at the
// gap between the merged intervals as answer.

// The time complexity for the above approach is O(nlogn), n being the total number of intervals.
// Since we sort all the intervals, the sorting part takes O(nlong). The merging time takes
// linear time, O(n), the looking part is linear time as well. So total time complexity is
// O(nlogn). Space complexity is O(n), n being the total number of intervals, since we are
// puting all the intervals in a priorityqueue.

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> intervals = new ArrayList<>();
        for (List<Interval> ls : schedule) {
            for (Interval i : ls) intervals.add(i);
        }
        Collections.sort(intervals, (a,b) -> a.start == b.start ? a.end - b.end : a.start - b.start);
        Deque<Interval> st = new ArrayDeque<>();
        for (Interval in : intervals) {
            int curr_start = in.start;
            int curr_end = in.end;
            if (st.isEmpty()) {
                st.push(in);
            } else if (st.peek().end >= curr_start) {
                Interval st_int = st.pop();
                if (st_int.end < curr_end) st_int.end = curr_end;
                st.push(st_int);
            } else {
                st.push(in);
            }
        }
        List<Interval> ans = new ArrayList<>();
        if (st.size() <= 1) return ans;
        Interval prev = st.pollLast();
        while (!st.isEmpty()) {
            Interval current = st.pollLast();
            Interval toAdd = new Interval(prev.end,current.start);
            ans.add(toAdd);
            prev = current;
        }
        return ans;
    }
}

// There is a way to improve this. Since it is given that the employee intervals are already
// sorted, we can simply just look at the first interval that hasn't been dealt with for
// each of the employees. The size of the priorityqueue would be reduced from n the total
// number of intervals to k the total number of employees. Time complexity would be O(nlogk)

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> schedule.get(a[0]).get(a[1]).start - schedule.get(b[0]).get(b[1]).start);
        for (int i = 0; i < schedule.size(); i++) {
            pq.add(new int[] {i, 0});
        }
        List<Interval> res = new ArrayList<>();
        int prev = schedule.get(pq.peek()[0]).get(pq.peek()[1]).start;
        while (!pq.isEmpty()) {
            int[] index = pq.poll();
            Interval interval = schedule.get(index[0]).get(index[1]);
            if (interval.start > prev) {
                res.add(new Interval(prev, interval.start));
            }
            prev = Math.max(prev, interval.end);
            if (schedule.get(index[0]).size() > index[1] + 1) {
                pq.add(new int[] {index[0], index[1] + 1});
            }
        }
        return res;
    }
}