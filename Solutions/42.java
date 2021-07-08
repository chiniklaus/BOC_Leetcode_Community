// 42. Trapping Rain Water

// dp to keep track of left max and right max. both O(n)
class Solution {
    public int trap(int[] height) {
        int len = height.length;
        if (len == 0) return 0;
        int[] maxleft = new int[len];
        int[] maxright = new int[len];
        maxleft[0] = 0;
        maxright[len-1] = 0;
        for (int i = 1; i < len; i++) maxleft[i] = Math.max(maxleft[i-1],height[i-1]);
        for (int i = len-2; i >= 0; i--) maxright[i] = Math.max(maxright[i+1], height[i+1]);
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            int level = Math.min(maxleft[i],maxright[i]);
            if (level > height[i]) cnt += level - height[i];
        }
        return cnt;
    }
}

// two pointer with O(1) space
int trap(vector<int>& height)
{
    int left = 0, right = height.size() - 1;
    int ans = 0;
    int left_max = 0, right_max = 0;
    while (left < right) {
        if (height[left] < height[right]) {
            height[left] >= left_max ? (left_max = height[left]) : ans += (left_max - height[left]);
            ++left;
        }
        else {
            height[right] >= right_max ? (right_max = height[right]) : ans += (right_max - height[right]);
            --right;
        }
    }
    return ans;
}