// 1268. Search Suggestions System

// Use a trie to store the products, and then write a pre order traversal algorithm to find the first three products

class Solution {
    
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> ans = new ArrayList<>();
        
        Node trie = new Node();
        
        for (String s : products) insert(s, trie);
        for (int i = 0; i < searchWord.length(); i++) {
            String target = searchWord.substring(0,i+1);
            ans.add(find(target,trie));
        }
        return ans;
        
    }
    
    private void insert(String string, Node trie) {
        Node curr = trie;
        for (char c : string.toCharArray()) {
            if (curr.children[c-'a'] == null) curr.children[c-'a'] = new Node();
            curr = curr.children[c-'a'];
        }
        curr.end = true;
    }
    
    private List<String> find(String string, Node trie) {
        Node curr = trie;
        StringBuilder sb = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (curr.children[c-'a'] == null) return new ArrayList<>();
            sb.append(c);
            curr = curr.children[c-'a'];
        }
        List<String> re = new ArrayList<>();
        preOrder(re, curr, sb);
        return re;
    }
    
    private void preOrder(List<String> re, Node trie, StringBuilder sb) {
        if (trie.end) re.add(sb.toString());
        if (re.size() == 3) return;
        for (int i = 0; i < 26; i++) {
            Node child = trie.children[i];
            if (child != null) {
                sb.append((char)('a'+i));
                preOrder(re, child, sb);
                if (re.size() == 3) return;
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}

class Node { 
    Node[] children;
    boolean end;
    
    public Node(){
        children = new Node[26];
        end = false;
    }
}