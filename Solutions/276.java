// 276. Paint Fence

// This is a DP problem. The transition function is, for dp[i], we can choose
// to paint i different color as prev, or the same color as prev
// For different color, there are dp[i-1] * (k-1) total ways to paint
// For same color, we need prev be different than prev's prev,
// and there are dp[i-2] * (k-1) ways to do that. So the total number for i
// is dp[i-1] * (k-1) + dp[i-2] * (k-1)

// Time O(n), space O(n)

class Solution {
    public int numWays(int n, int k) {
        if (n == 1) return k;
        int[] dp = new int[n+1];
        dp[1] = k;
        dp[2] = k * k;
        for (int i = 3; i <= n; i++) {
            dp[i] = (k-1) * (dp[i-1] + dp[i-2]);
        }
        return dp[n];
    }
}