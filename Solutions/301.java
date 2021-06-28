// 301. Remove Invalid Parentheses

// dfs + backtracking + tree pruning
class Solution {
    Set<String> set = new HashSet<>();
    public List<String> removeInvalidParentheses(String s) {
        int l = 0;
        int r = 0;
        int p = 0;
        while (p < s.length()) {
            if (r > l) break;
            char c = s.charAt(p);
            if (c == '(') l++;
            if (c == ')') r++;
            p++;
        }
        if (p == s.length() && l == r) {
            List<String> re = new ArrayList<>();
            re.add(s);
            return re;
        }
        for (int i = 1; i < s.length(); i++) {
            if (set.size() == 0) {
                int[] arr = new int[i];
                dfs(0, arr, 0, s, 0, 0);
            }
        }
        if (set.size() == 0) {
            List<String> rr = new ArrayList<>();
            rr.add("");
            return rr;
        }
        return new ArrayList<>(set);
    }
    private void dfs(int num_deleted, int[] arr, int curr, String s, int left, int right) {
        if (num_deleted == arr.length) {
            int l = left;
            int r = right;
            int po = curr;
            while (po < s.length()) {
                char c = s.charAt(po);
                if (c == '(') {
                    l++;
                } else if (c == ')') {
                    r++;
                    if (r > l) return;
                }
                po++;
            }
            if (l != r) return;
            StringBuilder sb = new StringBuilder(s);
            for (int j = arr.length-1; j >= 0; j--) {
                sb.deleteCharAt(arr[j]);
            }
            set.add(sb.toString());
            return;
        }
        if (s.length() - curr < arr.length - num_deleted || right > left) return;
        if (curr == arr.length && num_deleted == arr.length && left == right) {
            StringBuilder sb = new StringBuilder(s);
            for (int j = arr.length-1; j >= 0; j--) {
                sb.deleteCharAt(arr[j]);
            }
            set.add(sb.toString());
            return;
        }
        
        char c = s.charAt(curr);
        if (c == '(') {
            arr[num_deleted] = curr;
            dfs(num_deleted + 1, arr, curr+1, s, left, right);
            dfs(num_deleted, arr, curr+1, s, left+1, right);
        } else if (c == ')') {
            arr[num_deleted] = curr;
            dfs(num_deleted + 1, arr, curr+1, s, left, right);
            dfs(num_deleted, arr, curr+1, s, left, right+1);
        } else {
            dfs(num_deleted, arr, curr+1, s, left, right);
        }
    }
}