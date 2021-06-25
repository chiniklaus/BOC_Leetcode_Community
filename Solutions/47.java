// 47. Permutations II

// dfs with backtracking. for each position, we fill in the first element of
// all the duplicates. because after that, if we fill in another element that
// is the same as the first element, the permutation would be duplicate.

class Solution {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ls = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, ls, visited);
        return ans;
    }
    private void dfs(int[] nums, List<Integer> ls, boolean[] visited) {
        if (ls.size() == nums.length) {
            ans.add(new ArrayList<>(ls));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            if (!visited[i]) {
                visited[i] = true;
                ls.add(nums[i]);
                dfs(nums, ls, visited);
                ls.remove(ls.size()-1);
                visited[i] = false;
            }
        }
    }
}