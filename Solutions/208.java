// 208. Implement Trie (Prefix Tree)

// Basically we need to know what a trie is to solve this problem
// A trie is a tree data structure to store strings. Each node represents one character
// Each node has 26 subnodes to mark if the future path of a string
// Each node has a boolean variable to indicate if the current node is the end of a path

// Time complexity is O(l) to search and insert, l is the length of a string
// Space complexity is O(l*n)

class Trie {

    class Node {
        Node[] children;
        boolean end;
        public Node(){
            children = new Node[26];
            end = false;
        }
    }
    
    private Node node;
    
    /** Initialize your data structure here. */
    public Trie() {
        node = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node curr = node;
        for (char c : word.toCharArray()) {
            if (curr.children[c-'a'] == null) {
                Node child = new Node();
                curr.children[c-'a'] = child;
            }
            curr = curr.children[c-'a'];
        }
        curr.end = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node curr = node;
        for (char c : word.toCharArray()) {
            if (curr.children[c-'a'] == null) return false;
            curr = curr.children[c-'a'];
        }
        return curr.end;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node curr = node;
        for (char c : prefix.toCharArray()) {
            if (curr.children[c-'a'] == null) return false;
            curr = curr.children[c-'a'];
        }
        if (curr.end == true) return true;
        for (Node n : curr.children) if (n != null) return true;
        return false;
    }
}