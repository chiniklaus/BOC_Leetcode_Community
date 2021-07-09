// 1004. Max Consecutive Ones III

// sliding window O(n)
class Solution {
    public int longestOnes(int[] nums, int k) {
        int p = 0;
        int q = 0;
        int flips = 0;
        int max = 0;
        while (q < nums.length) {
            while (q < nums.length && (nums[q] == 1 || flips < k)) {
                if (nums[q] == 0) flips++;
                q++;
            }
            
            max = Math.max(max, q-p);
            while (p < nums.length && nums[p] == 1 && p < q) p++;
            flips--;
            p++;
        }
        return max;
    }
}

// dp O(nk)
class Solution {
    public int longestOnes(int[] nums, int k) {
        int[] dp = new int[k+1];
        if (nums[0] == 1) {
            dp[0] = 1;
        } else {
            if (k != 0) dp[1] = 1;
        }
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = Math.min(i+1, k); j >= 0; j--) {
                if (nums[i] == 1) {
                    dp[j]++;
                    max = Math.max(max, dp[j]);
                } else {
                    if (j == 0) {
                        dp[j] = 0;
                    } else {
                        dp[j] = dp[j-1] + 1;
                        max = Math.max(max, dp[j]);
                    }
                }
            }
        }
        return max;
    }
}