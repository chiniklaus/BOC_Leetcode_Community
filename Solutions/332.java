// 332. Reconstruct Itinerary

// Well initial thought, like any sane person, is DFS + memorization. With a O(n^k) time complexity,
// k being the largest out degree.

// I used dfs and a boolean array to keep track of what tickets I used. At first I tried using heap
// and treeset to maintain the order of adjacent airports. But can't iterate heap, can't add duplicate
// to treeset. So I thought oh fudge it. I will just use a list and sort the crab out of it. And it 
// worked with decent performance. 

// When nothing works, just sort man. Just sort.


class Solution {
    Map<String, List<Integer>> map;
    public List<String> findItinerary(List<List<String>> tickets) {
        map = new HashMap<>();
        for (int i = 0; i < tickets.size(); i++) {
            List<String> list = tickets.get(i);
            String from = list.get(0);
            String to = list.get(1);
            if (!map.containsKey(from)) map.put(from, new ArrayList<>());
            map.get(from).add(i);
        }
        for (Map.Entry<String,List<Integer>> e : map.entrySet()) {
            List<Integer> ls = e.getValue();
            Collections.sort(ls, (a,b) -> tickets.get(a).get(1).compareTo(tickets.get(b).get(1)));
        }
        boolean[] seen = new boolean[tickets.size()];
        List<String> ans = new ArrayList<>();
        ans.add("JFK");
        dfs("JFK", seen, ans, tickets);
        return ans;
    }
    private void dfs(String from, boolean[] seen, List<String> ans, List<List<String>> tickets) {
        if (!map.containsKey(from)) return;
        List<Integer> list = map.get(from);
        for (Integer curr : list) {
            if (!seen[curr]) {
                String next = tickets.get(curr).get(1);
                ans.add(next);
                if (ans.size() == seen.length + 1) return;
                seen[curr] = true;
                dfs(next, seen, ans, tickets);
                if (ans.size() == seen.length + 1) return;
                seen[curr] = false;
                ans.remove(ans.size()-1);
            }
        }
        return;
    }
}
 

// But then there is this black magic solution called Hierholzer's Algorithm with Time 
// O(∣E∣log∣V∣∣E∣​) .


class Solution {
    Map<String, PriorityQueue<String>> map;
    public List<String> findItinerary(List<List<String>> tickets) {
        map = new HashMap<>();
        for (List<String> ls : tickets) {
            String from = ls.get(0);
            String to = ls.get(1);
            if (!map.containsKey(from)) map.put(from, new PriorityQueue<String>((a,b) -> a.compareTo(b)));
            map.get(from).add(to);
        }
        boolean[] seen = new boolean[tickets.size()];
        List<String> ans = new ArrayList<>();
        go(ans, "JFK");
        return ans;
    }
    private void go(List<String> ans, String from) {
        if (!map.containsKey(from)) {
            ans.add(0, from);
            return;
        }
        PriorityQueue<String> heap = map.get(from);
        while (heap.size() != 0) {
            go(ans, heap.remove());
        }
        ans.add(0, from);
    }
}