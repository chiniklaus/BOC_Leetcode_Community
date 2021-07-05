// 297. Serialize and Deserialize Binary Tree

// we can do pre-order with time O(n), spaceO(h)

public class Codec {
    int pointer = 0;
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize_helper(root, sb);
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    
    private void serialize_helper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null ");
            return;
        }
        sb.append(node.val);
        sb.append(' ');
        serialize_helper(node.left,sb);
        serialize_helper(node.right,sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(" ");
        return deserialize_helper(arr);
    }
    
    private TreeNode deserialize_helper(String[] arr) {
        if (arr[pointer].equals("null")) {
            pointer++;
            return null;
        }
        TreeNode curr = new TreeNode(Integer.valueOf(arr[pointer]));
        pointer++;
        curr.left = deserialize_helper(arr);
        curr.right = deserialize_helper(arr);
        return curr;
    }
}

// we can do this post-order

public class Codec {
    int pointer = 0;
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize_helper(root, sb);
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    
    private void serialize_helper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null");
            sb.append(' ');
            return;
        }
        serialize_helper(node.left, sb);
        serialize_helper(node.right, sb);
        sb.append(node.val);
        sb.append(' ');
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(" ");
        pointer = arr.length-1;
        return deserialize_helper(arr);
    }
    
    private TreeNode deserialize_helper(String[] arr) {
        if (arr[pointer].equals("null")) {
            pointer--;
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(arr[pointer]));
        pointer--;
        node.right = deserialize_helper(arr);
        node.left = deserialize_helper(arr);
        return node;
    }
}

// we can do this level-order
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                sb.append("null");
                sb.append(' ');
                continue;
            }
            sb.append(curr.val);
            sb.append(' ');
            queue.offer(curr.left);
            queue.offer(curr.right);
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(" ");
        int pointer = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        if (arr.length == 1) return null;
        TreeNode node = new TreeNode(Integer.valueOf(arr[0]));
        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            String left = arr[pointer];
            String right = arr[pointer+1];
            if (!left.equals("null")) {
                TreeNode leftNode = new TreeNode(Integer.valueOf(left));
                curr.left = leftNode;
                queue.offer(leftNode);
            }
            if (!right.equals("null")) {
                TreeNode rightNode = new TreeNode(Integer.valueOf(right));
                curr.right = rightNode;
                queue.offer(rightNode);
            }
            pointer = pointer + 2;
        }
        return node;
    }
}