// 933. Number of Recent Calls

// binary search O(logn)
class RecentCounter {

    List<Integer> ls;
    
    public RecentCounter() {
        ls = new ArrayList<>();
    }
    
    public int ping(int t) {
        ls.add(t);
        int limit = t - 3000;
        int p = 0;
        int q = ls.size()-1;
        if (p == q) return 1;
        if (p + 1 == q) {
            if (ls.get(p) < limit) return 1;
            return 2;
        }
        int mid;
        while (p + 1 < q) {
            mid = (q-p)/2+p;
            if (ls.get(mid) >= limit) {
                q = mid - 1;
            } else {
                p = mid;
            }
        }
        // p p+1 q
        // 0 1 2
        if (ls.get(p) >= limit) return ls.size()-p;
        if (ls.get(p+1) >= limit) return ls.size()-p-1;
        if (ls.get(q) >= limit) return ls.size()-q;
        return ls.size()-q-1;
    }
}

// sliding window O(1)
class RecentCounter {

    List<Integer> ls;
    int pointer;
    
    public RecentCounter() {
        ls = new ArrayList<>();
        pointer = 0;
    }
    
    public int ping(int t) {
        ls.add(t);
        int size = ls.size();
        int limit = t - 3000;
        while (pointer < size && ls.get(pointer) < limit) pointer++;
        return size - pointer;
    }
}