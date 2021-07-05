// 139. Word Break

// there is the obvious dfs + backtracking solution. not so smart, not so fast. we will be doing a lot
// of redundant work. i guess for problems like this, what we need to think about is how we can reduce
// the redundant work, memorize the work we have already done. i guess that is the essence of dp

// for this problem, we can use dp.
// dp[n] = from dp[0] to dp[n-1] if true, and contains substring i+1 n+1

// time O(n), space O(L)

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String str : wordDict) set.add(str);
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.substring(0,i+1))) {
                dp[i] = true;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j+1,i+1))) dp[i] = true;
            }
        }
        return dp[s.length()-1];
    }
}

// bfs solution with same time complexity as dp solution
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] seen = new boolean[s.length()];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == seen.length) return true;
            if (seen[curr]) continue;
            seen[curr] = true;
            for (int i = curr; i < s.length(); i++) {
                if (set.contains(s.substring(curr,i+1))) {
                    queue.offer(i+1);
                }
            }
        }
        return false;
    }
}