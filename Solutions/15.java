// 15. 3Sum

// slow as fuck O(n^2) solution with tons of overhead
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (!map.containsKey(nums[i])) map.put(nums[i],new HashSet<>());
            map.get(nums[i]).add(i);
        }
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int curr_sum = nums[i] + nums[j];
                if (map.containsKey(0-curr_sum)) {
                    List<Integer> cache = new ArrayList<>();
                    cache.add(nums[i]);
                    cache.add(nums[j]);
                    cache.add(0-curr_sum);
                    Collections.sort(cache);
                    String code = cache.get(0) + " " + cache.get(1) + " " + cache.get(2);
                    if (seen.contains(code)) continue;
                    Set<Integer> set = map.get(0-curr_sum);
                    for (Integer in : set) {
                        if (in != i && in != j) {
                            ans.add(cache);
                            seen.add(code);
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }
}

