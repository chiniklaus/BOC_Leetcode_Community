// 146. LRU Cache

// use doublely linked linkedlist to store relative order between key pairs

class LRUCache {
    Map<Integer, PairNode> map; 
    int cap;
    PairNode head;
    PairNode tail;
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.cap = capacity;
        head = null;
        tail = null;
    } 
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        PairNode curr = map.get(key);
        if (curr.prev == null) {
            return curr.value;
        } else {
            if (curr.next == null) {
                PairNode p = curr.prev;
                p.next = null;
                tail = p;
                curr.next = head;
                head.prev = curr;
                head = curr;
                curr.prev = null;
            } else {
                PairNode p = curr.prev;
                PairNode n = curr.next;
                p.next = n;
                n.prev = p;
                curr.next = head;
                head.prev = curr;
                head = curr;
                curr.prev = null;
            }
        }
        return curr.value;
    }
    public void put(int key, int value) {
        PairNode node = new PairNode();
        node.key = key;
        node.value = value;
        if (!map.containsKey(key)) {
            if (map.size() < cap) {
                if (head == null) {
                    head = node;
                    tail = node;
                } else {
                    head.prev = node;
                    node.next = head;
                    head = node;
                }
                map.put(key, node);
            } else {
                map.remove(tail.key);
                if (tail.prev == null) {
                    head = node;
                    tail = node;
                } else {
                    PairNode p = tail.prev;
                    tail = p;
                    p.next = null;
                    node.next = head;
                    head.prev = node;
                    head = node;
                }
                map.put(key,node);
            }
        } else {
            PairNode curr = map.get(key);
            curr.value = value;
            if (curr.prev == null) {
                map.put(key, curr);
            } else {
                if (curr.next == null) {
                    PairNode p = curr.prev;
                    tail = p;
                    p.next = null;
                    curr.next = head;
                    head.prev = curr;
                    head = curr;
                    curr.prev = null;
                } else {
                    PairNode p = curr.prev;
                    PairNode n = curr.next;
                    p.next = n;
                    n.prev = p;
                    curr.next = head;
                    head.prev = curr;
                    head = curr;
                    curr.prev = null;
                }
                map.put(key, curr);
            }
        }
    }
}

class PairNode {
    PairNode prev;
    PairNode next;
    int key;
    int value;
    public PairNode(){
        this.prev = null;
        this.next = null;
        this.key = 0;
        this.value = 0;
    }
}