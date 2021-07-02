// 366. Find Leaves of Binary Tree

// many solutions for this one

// BFS, traverse entire tree twice
class Solution {
    Queue<Node> queue;
    public List<List<Integer>> findLeaves(TreeNode root) {
        queue = new LinkedList<>();
        Node head = make(root, null);
        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                Node curr = queue.poll();
                level.add(curr.val);
                if (curr.parent == null) break;
                if (curr.parent.right == curr) {
                    curr.parent.right = null;
                } else {
                    curr.parent.left = null;
                }
                if (curr.parent.left == null && curr.parent.right == null) {
                    queue.offer(curr.parent);
                }
            }
            ans.add(level);
        }
        return ans;
    }
    private Node make(TreeNode node, Node parent) {
        Node curr = new Node(node.val);
        curr.parent = parent;
        if (node.left == null) {
            if (node.right == null) {
                queue.offer(curr);
            } else {
                curr.right = make(node.right, curr);
            }
        } else {
            if (node.right == null) {
                curr.left = make(node.left, curr);
            } else {
                curr.left = make(node.left, curr);
                curr.right = make(node.right, curr);
            }
        }
        return curr;
    }
}

class Node {
    int val;
    Node left;
    Node right;
    Node parent;
    Node() {}
    Node(int val) { this.val = val; }
    Node(int val, Node left, Node right, Node parent) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}

// DFS, pre-order + hashing
class Solution {
    Set<TreeNode> set = new HashSet<>();
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        while(!set.contains(root)) {
            List<Integer> level = new ArrayList<>();
            dfs(level, root);
            ans.add(level);
        }
        return ans;
    }
    private void dfs(List<Integer> level, TreeNode node) {
        if (node == null || set.contains(node)) return;
        if ((node.left == null || set.contains(node.left)) && ((node.right == null) || (set.contains(node.right)))) {
            level.add(node.val);
            set.add(node);
            return;
        }
        dfs(level, node.left);
        dfs(level, node.right);
    }
}