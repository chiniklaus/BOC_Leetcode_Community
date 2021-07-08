// 189. Rotate Array

// use a new array, both time and space O(n)
class Solution {
    public void rotate(int[] nums, int k) {
        int[] nnums = new int[nums.length];
        int pointer = 0;
        int len = nums.length;
        int npointer = k % len;
        for (int i = npointer; i < len; i++) {
            nnums[i] = nums[pointer];
            pointer++;
        }
        int p = 0;
        while (pointer < len) {
            nnums[p] = nums[pointer];
            p++;
            pointer++;
        }
        for (int i = 0; i < len; i++) nums[i] = nnums[i];
    }
}

// reverse O(1) space
class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}