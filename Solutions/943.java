943. Find the Shortest Superstring

// permutation. a heroic try. too slow.

class Solution {
    String ans = "";
    int mlen = Integer.MAX_VALUE;
    String[] words;
    Map<Integer, Map<Integer, Integer>> pre = new HashMap<>();
    private void tryit(int first, int second) {
        String s1 = words[first];
        String s2 = words[second];
        
        int pointer = 0;
        int lastp = 1;
        int bigp = 1;
        boolean found = false;
        while (bigp < s1.length()) {
            if (lastp == s1.length()) {
                int re = lastp - pointer;
                if (!pre.containsKey(first)) pre.put(first, new HashMap<>());
                pre.get(first).put(second,re);
                found = true;
                break;
            }
            if (s1.charAt(lastp) == s2.charAt(pointer)) {
                lastp++;
                pointer++;
            } else {
                bigp++;
                lastp = bigp;
                pointer = 0;
            }
        }
        if (!found) {
            if (!pre.containsKey(first)) pre.put(first, new HashMap<>());
            pre.get(first).put(second,0);
        }
    } 
    public String shortestSuperstring(String[] words) {
        this.words = words;
        List<Integer> ls = new ArrayList<>();
        boolean[] seen = new boolean[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i != j) {
                    tryit(i,j);
                }
            }
        }
        dfs(words.length, seen, ls);
        return ans;
    }
    private void dfs(int len, boolean[] seen, List<Integer> ls) {
        if (ls.size() == len) {
            StringBuilder sb = new StringBuilder();
            sb.append(words[ls.get(0)]);
            int words_pointer = 1;
            int p = 0;
            int ls_size = ls.size();
            while (words_pointer < ls_size) {
                int first = ls.get(words_pointer-1);
                int second = ls.get(words_pointer);
                int offset = pre.get(first).get(second);
                //System.out.println(offset);
                if (offset != 0) {
                    sb.delete(p+offset, sb.length());
                    p += offset;
                    sb.append(words[second]);
                } else {
                    sb.append(words[second]);
                    p += words[first].length();
                }
                words_pointer++;
            }
            if (mlen > sb.length()) {
                ans = sb.toString();
                mlen = sb.length();
            }
            return;
        }
        
        for (int i = 0; i < len; i++) {
            if (!seen[i]) {
                seen[i] = true;
                ls.add(i);
                dfs(len, seen, ls);
                ls.remove(ls.size()-1);
                seen[i] = false;
            }
        }
    }
}