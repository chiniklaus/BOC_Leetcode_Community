// 523. Continuous Subarray Sum

// I guess this is kind of a DP thinking process. We look at the sum up to i index. If the sum up to that point
// is a multiple of k, then we return true. If a mod of sum % k exists in the previous sums we have seen, we know
// we can return true. Because we can minus that previous sum, and the bad part (sum % k) got cut off, the sub array
// sum is guaranteed to be a multiple of k.

// Time O(n), Space O(n)
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int curr = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int prev = curr;
            curr += nums[i];
            int mod = curr % k;
            if (mod == 0 || set.contains(mod)) return true;
            set.add(prev % k);
        }
        return false;
    }
}