// 454. 4Sum II

// use hashmap to store sum and sum count for first two sums
// for the rest two sums, check if the first two sum stored required sums

// time O(n^2)

class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int len = nums1.length;
        Map<Integer,Integer> map1 = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int sum = nums1[i] + nums2[j];
                if (!map1.containsKey(sum)) map1.put(sum,0);
                map1.put(sum,map1.get(sum)+1);
            }
        }
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int sum = nums3[i] + nums4[j];
                if (map1.containsKey(-sum)) cnt += map1.get(-sum);
            }
        }
        return cnt;
    }
}