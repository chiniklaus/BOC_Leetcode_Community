// 472. Concatenated Words

// this problem is basically word break, we just run every candidate with word break solution

// time O(n^3 * l), space O(n)
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, (a,b) -> a.length()-b.length());
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (String st : words) set.add(st);
        for (String s : words) {
            set.remove(s);
            if (check(s,set)) ans.add(s);
            set.add(s);
        }
        return ans;
    }
    private boolean check(String s, Set<String> set) {
        if (s.length() == 0) return false;
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(0,i+1);
            if (set.contains(sub)) {
                dp[i] = true;
                continue;
            }
            for (int j = 0; j <= i; j++) {
                if(dp[j] && set.contains(s.substring(j+1,i+1))) dp[i] = true;
            }
        }
        return dp[s.length()-1];
    }
}