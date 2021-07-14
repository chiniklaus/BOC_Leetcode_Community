// 1395. Count Number of Teams

// we want to know for each number i, how many numbers on the left are bigger than i and how
// many number on the right are smaller than i. mutiply those two numbers give us the answer
// of a rating[m] > rarting[i] > rating[n] situation. we do the reverse as well and add all
// the answers.

// time O(nlogn)

class Solution {
    public int numTeams(int[] rating) {
        int len = rating.length;
        int[] on_the_left = new int[len];
        int[] on_the_right = new int[len];
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int position = insert(left,rating[i]);
            on_the_left[i] = left.size()-position-1;
        }
        for (int i = len-1; i >= 0; i--) {
            int position = insert(right,rating[i]);
            on_the_right[i] = position;
        }
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            cnt += on_the_left[i] * on_the_right[i];
            cnt += (i - on_the_left[i]) * (len - i - on_the_right[i]-1);
        }
        return cnt;
    }
    private int insert(List<Integer> ls, int num) {
        if (ls.size() == 0) {
            ls.add(num);
            return 0;
        }
        int p = 0;
        int q = ls.size()-1;
        int mid;
        if (p == q) {
            if (ls.get(p) > num) {
                ls.add(0,num);
                return 0;
            } else {
                ls.add(num);
                return p+1;
            }
        }
        while (p < q) {
            mid = (q-p)/2+p;
            if (ls.get(mid) > num) {
                q = mid;
            } else {
                p = mid+1;
            }
        }
        if (ls.get(p) < num) {
            ls.add(num);
            return ls.size()-1;
        }
        ls.add(p,num);
        return p;
    }
}