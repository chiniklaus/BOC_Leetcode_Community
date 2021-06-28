// 315. Count of Smaller Numbers After Self

// Initial thought is to maintain a sorted list of numbers on the right of the current number i. 
// For each number i we do a binary search to find how many is smaller on the right and insert i to the sorted list. Time O(nlogn)

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ls = new ArrayList<>();
        int[] ans = new int[nums.length];
        for (int i = nums.length-1; i >= 0; i--) {
            ans[i] = insert(ls, nums[i]);
        }
        List<Integer> re = new ArrayList<>();
        for (int i : ans) re.add(i);
        return re;
    }
    private int insert(List<Integer> ls, int num) {
        if (ls.size() == 0) {
            ls.add(num);
            return 0;
        }
        
        int p = 0;
        int q = ls.size()-1;
        int mid;
        while (p + 1 < q) {
            mid = (q-p)/2+p;
            if (ls.get(mid) < num) {
                p = mid;
            } else {
                q = mid - 1;
            }
        }
        int pval = ls.get(p);
        int qval = ls.get(q);
        if (pval < num) {
            if (qval < num) {
                ls.add(q+1, num);
                return q+1;
            } else {
                ls.add(p+1, num);
                return p+1;
            }
        } else {
            ls.add(0, num);
            return 0;
        }
    }
}