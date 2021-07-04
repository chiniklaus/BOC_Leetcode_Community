// 1202. Smallest String With Swaps

// There are three steps
// 1. put all indices in a disjoint-set
// 2. for each group in the disjoint-set, we record their characters and put them all in a heap, map the
// group parent with the heap
// 3. iterate from 0 to n, find parent of i, and pick a character from the heap associated to the parent

// Time (L + N + NlogN)

class Solution {
    private int[] parent;
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (s == null || s.length() == 0) {
            return null;
        }
        parent = new int[s.length()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        
        for (List<Integer> pair : pairs) {
            union(pair.get(0), pair.get(1));
        }
        
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        char[] sChar = s.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            int root = find(i);
            map.putIfAbsent(root, new PriorityQueue<>());
            map.get(root).offer(sChar[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sChar.length; i++) {
            sb.append(map.get(find(i)).poll());
        }
        return sb.toString();
    }
    private int find(int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }
    private void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else {
            parent[aParent] = bParent;
        }
    }
}

// DFS
class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        Set<Integer> seen = new HashSet<Integer>();
        List<Set<Integer>> graph = new ArrayList<Set<Integer>>();
        int len = s.length();
        
        for (int i = 0; i < len; i++) {
            graph.add(new HashSet<Integer>());
        }
        
        for (List<Integer> l : pairs) {
            graph.get(l.get(0)).add(l.get(1));
            graph.get(l.get(1)).add(l.get(0));
        }
        
        int current = 0;
        
        char[] chars = s.toCharArray();
        
        String a = "abcdefghijklmnopqrstuvwxyz";
        char[] al_array = a.toCharArray();
        
        while (seen.size() != len) {
            int[] alph = new int[26];
            PriorityQueue<Integer> swap_indices = new PriorityQueue<Integer>();
            dfs(current, graph, seen, alph, swap_indices, chars);
            List<Character> swap_char = new ArrayList<Character>();
            for (int i = 0; i < 26; i++) {
                while (alph[i] != 0) {
                    alph[i]--;
                    swap_char.add(al_array[i]);
                }
            }
            int swap_char_pointer = 0;
            while (swap_indices.size() != 0) {
                int i = swap_indices.poll();
                chars[i] = swap_char.get(swap_char_pointer);
                swap_char_pointer++;
            }
            while (seen.contains(current)) current++;
        }
        
        return String.valueOf(chars);
    }
    
    private void dfs(int current, List<Set<Integer>> graph, Set<Integer> seen, int[] alph, PriorityQueue swap_indices, char[] chars) {
        alph[chars[current] - 'a']++;
        swap_indices.add(current);
        seen.add(current);
        for (Integer i : graph.get(current)) {
            if (!seen.contains(i)) {
                dfs(i, graph, seen, alph, swap_indices, chars);
            }
        }
    }
}