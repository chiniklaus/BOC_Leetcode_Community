// 140. Word Break II

// well
// first solution is BFS. The idea is building on valid substring untill we reach the full string. BFS tries all possible routes.

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < wordDict.size(); i++) map.put(wordDict.get(i),i);
		// the pair is: index, list of combination of words lead up to this index
        Queue<Pair<Integer, List<Integer>>> queue = new LinkedList<>();
        Pair<Integer, List<Integer>> begin = new Pair(0, new ArrayList<>());
        queue.offer(begin);
        while (!queue.isEmpty()) {
            Pair<Integer, List<Integer>> pair = queue.poll();
            int index = pair.getKey();
            List<Integer> history = pair.getValue();
            if (index == s.length()) {
                StringBuilder sb = new StringBuilder();
                for (int i : history) {
                    sb.append(wordDict.get(i));
                    sb.append(' ');
                }
                sb.deleteCharAt(sb.length()-1);
                ans.add(sb.toString());
                continue;
            }
            for (int i = index; i < s.length(); i++) {
                String substring = s.substring(index,i+1);
                if(map.containsKey(substring)) {
                    List<Integer> newlist = new ArrayList<>(history);
                    newlist.add(map.get(substring));
                    queue.offer(new Pair(i+1,newlist));
                }
            }
        }
        return ans;
    }
}

// dfs + backtracking. i guess this solution is using the same idea as the bfs one
class Solution {
    List<String> ans = new ArrayList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<Integer> cache = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < wordDict.size(); i++) map.put(wordDict.get(i),i);
        dfs(cache, s, 0, map, wordDict);
        return ans;
    }
    private void dfs(List<Integer> cache, String s, int pointer, Map<String,Integer> map, List<String> wordDict) {
        if (pointer == s.length()) {
            StringBuilder sb = new StringBuilder();
            for (int i : cache) {
                sb.append(wordDict.get(i));
                sb.append(' ');
            }
            sb.deleteCharAt(sb.length()-1);
            ans.add(sb.toString());
            return;
        }
        for (int i = pointer; i < s.length(); i++) {
            String substring = s.substring(pointer, i+1);
            if (map.containsKey(substring)) {
                cache.add(map.get(substring));
                dfs(cache,s,i+1,map,wordDict);
                cache.remove(cache.size()-1);
            }
        }
    }
}