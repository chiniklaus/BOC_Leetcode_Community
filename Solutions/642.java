// 642. Design Search Autocomplete System

// Use Trie data structure to do this.

// Time O(n * l), space O(n * l)

class AutocompleteSystem {

    class Node {
        Node[] children;
        int hot;
        public Node() {
            this.children = new Node[27];
            hot = 0;
        }
    }
    
    Node node;
    StringBuilder sb;
    Node searchCurrent;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        node = new Node();
        for (int i = 0; i < sentences.length; i++) {
            int hotness = times[i];
            String s = sentences[i];
            Node curr = node;
            for (char c : s.toCharArray()) {
                if (c == ' ') {
                    if (curr.children[26] == null) curr.children[26] = new Node();
                    curr = curr.children[26];
                } else {
                    if (curr.children[c-'a'] == null) curr.children[c-'a'] = new Node();
                    curr = curr.children[c-'a'];
                }
            }
            curr.hot = hotness;
        }
        this.sb = new StringBuilder();
        this.searchCurrent = node;
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            searchCurrent.hot = searchCurrent.hot + 1;
            searchCurrent = node;
            sb.setLength(0);
            return new ArrayList<>();
        } else if (c == ' ') {
            if (searchCurrent.children[26] == null) searchCurrent.children[26] = new Node();
            searchCurrent = searchCurrent.children[26];
            sb.append(c);
            return findRecommendation();
        } else {
            if (searchCurrent.children[c-'a'] == null) searchCurrent.children[c-'a'] = new Node();
            searchCurrent = searchCurrent.children[c-'a'];
            sb.append(c);
            return findRecommendation();
        }
    }
    
    private List<String> findRecommendation() {
        List<String> ans = new ArrayList<>();
        PriorityQueue<Pair<String,Integer>> heap = new PriorityQueue<>((a,b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());
        dfs(heap,searchCurrent);
        int count = 0;
        while (!heap.isEmpty() && count < 3) {
            count++;
            Pair<String,Integer> rec = heap.poll();
            ans.add(rec.getKey());
        }
        return ans;
    }
    
    private void dfs(PriorityQueue<Pair<String,Integer>> heap, Node cnode) {
        if (cnode.hot != 0) heap.add(new Pair(sb.toString(),cnode.hot));
        for (int i = 0; i < 27; i++) {
            Node child = cnode.children[i];
            if (child != null) {
                if (i == 26) {
                    sb.append(' ');
                } else {
                    sb.append((char)('a' + i));
                }
                dfs(heap,child);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}