// 209. Minimum Size Subarray Sum

// sliding window + prefix sum O(n) time O(n) space
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        int len = nums.length;
        int[] pre = new int[len];
        pre[0] = nums[0];
        for (int i = 1; i < len; i++) pre[i] = pre[i-1] + nums[i];
        int p = 0;
        int q = 0;
        while (q < len) {
            int local_sum = pre[q] - pre[p] + nums[p];
            if (local_sum >= s) {
                min = Math.min(min, q-p+1);
                if (p == q) return 1;
                p++;
            } else {
                q++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}