// 1. Two Sum

// An O(n) solution is to remember what value we have seen. For each new number we see, if we have seen the
// complementary number, we found the answer. Since need to return indices, we use a map to remember the 
// value-index pair

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int tar = target-nums[i];
            if (map.containsKey(tar)) {
                return new int[]{map.get(tar),i};
            } else {
                map.put(nums[i],i);
            }
        }
        return new int[2];
    }
}

// O(n^2) brute force
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]+nums[j] == target) return new int[]{i,j};
            }
        }
        return new int[2];
    }
}