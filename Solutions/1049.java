// 1049. Last Stone Weight II

// This problem can be reduced to: Split the stones into two groups, and return the minimum
// difference between the two groups.

// So we want to get the total weight sum, and then minus the possible weight that closest to
// the mid point of the total weight sum, minus it twice to get the minimum difference.

// Time O(ns), Space (s), s being the total sum
class Solution {
    public int lastStoneWeightII(int[] A) {
        boolean[] weights = new boolean[3001];
        weights[0] = true;
        int curr = 0;
        for (int i : A) {
            curr += i;
            for (int j = curr; j >= i; j--) {
                if (weights[j-i]) weights[j] = true;
            }
        }
        for (int i = curr/2; i >= 0; i--) if (weights[i]) return curr - 2 * i;
        return 0;
    }
}