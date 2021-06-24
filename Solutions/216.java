216. Combination Sum III

// so for a combination problem, we use dfs + backtrack to check all the possibilities.

class Solution {
    
    List<List<Integer>> ans = new ArrayList<>();
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> ls = new ArrayList<>();
        dfs(ls, 0, n, k, 0);
        return ans;
    }
    
    private void dfs(List<Integer> ls, int sum, int target, int left, int curr) {
        if (sum == target && left == 0) {
            ans.add(new ArrayList<>(ls));
            return;
        }
        if (sum > target || left == 0) return;
        
        for (int i = curr+1; i <= 9; i++) {
            ls.add(i);
            dfs(ls, sum + i, target, left-1, i);
            ls.remove(ls.size()-1);
        }
    }
}