// 718. Maximum Length of Repeated Subarray

// use map to store values with positions, and do a double for loop for both arrays using the maps
// O(nm)
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Set<Integer>> map2 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            int curr = nums1[i];
            if (!map.containsKey(curr)) map.put(curr,new HashSet<>());
            map.get(curr).add(i);
        }
        for (int i = 0; i < nums2.length; i++) {
            int curr = nums2[i];
            if (!map2.containsKey(curr)) map2.put(curr,new HashSet<>());
            map2.get(curr).add(i);
        }
        int max = 0;
        for (Map.Entry<Integer, Set<Integer>> e : map.entrySet()) {
            int s1 = e.getKey();
            if (!map2.containsKey(s1)) continue;
            Set<Integer> set1 = map.get(s1);
            Set<Integer> set2 = map2.get(s1);
            for (Integer i : set1) {
                for (Integer j : set2) {
                    int p1 = i;
                    int p2 = j;
                    while (p1 < len1 && p2 < len2 && nums1[p1] == nums2[p2]) {
                        p1++;
                        p2++;
                    }
                    max = Math.max(max, p1-i);
                }
            }
        }
        return max;
    }
}

// dp with O(mn)
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1][len2];
        int max = 0;
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2-1; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    if (i == len1-1) {
                        dp[i][j] = 1;
                    } else if (j == len2-1) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i+1][j+1] + 1;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }
}

// sliding window
class Solution {
    public int findLength(int[] A, int[] B) {
        int result = 0;
        for (int i = 0; i < A.length + B.length - 1; ++i) {
            // The current overlapping window is A[aStart, Math.min(A.length, B.length)] and B[bStart, Math.min(A.length, B.length)].
            int aStart = Math.max(0, A.length - 1 - i);  
            int bStart = Math.max(0, i - (A.length - 1));
            int numConsecutiveMatches = 0;
            for (int aIdx = aStart, bIdx = bStart; aIdx < A.length && bIdx < B.length; ++aIdx, ++bIdx) {
                // Maintain number of equal consecutive elements in the current window (overlap) and the max number ever computed.
                numConsecutiveMatches = A[aIdx] == B[bIdx] ? numConsecutiveMatches + 1 : 0;
                result = Math.max(result, numConsecutiveMatches);
            }
        }
        return result;
    }
}