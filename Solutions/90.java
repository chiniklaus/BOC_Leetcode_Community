90. Subsets II

// The idea is dfs + backtracking because backtracking tries all the possible
// routes in the dfs. One thing special about this problem is that when we are
// looking at a number, and ready to add this number to potential subset and
// goes on with the dfs, we need to skip all the same numbers in this for loop
//
//   [<--skip-->]
// 1 1 1 1 1 1 1 2 3 4 5 6
// because in this for loop, we add the first 1 to potential answer array, then
// we dfs starting at next index. All the subsets starting from the second 1 have
// already been calculated. If we use the senond 1 or any other 1s to dfs, they
// will only create duplicates because all the possible combination of 1s should
// have already been added to ans

class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ls = new ArrayList<>();
        dfs(nums, ls, 0);
        return ans;
    }
    private void dfs(int[] nums, List<Integer> ls, int p) {
        ans.add(new ArrayList<>(ls));
        for (int i = p; i < nums.length; i++) {
            if (i > p && nums[i] == nums[i-1]) continue;
            ls.add(nums[i]);
            dfs(nums,ls,i+1);
            ls.remove(ls.size()-1);
        }
    }
}