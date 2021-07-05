// 23. Merge k Sorted Lists

// this is merge two sorted lists but with k. we can merge one by one, we can also merge less times
// in a divide and conquer fashion

// time O(nlogn), space O(logl)

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) return null;
        return mergeHelp(lists,0,len-1);
    }
    private ListNode mergeHelp(ListNode[] lists, int p, int q) {
        if (p == q) return lists[p];
        if (p - q == 1) {
            return mergeTwo(lists[p],lists[q]);
        }
        int mid = (p+q)/2;
        return mergeTwo(mergeHelp(lists,p,mid),mergeHelp(lists,mid+1,q));
    }
    private ListNode mergeTwo(ListNode n1, ListNode n2) {
        if (n1 == null) return n2;
        if (n2 == null) return n1;
        ListNode res = null;
        ListNode curr = null;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                if (res == null) {
                    res = n1;
                    n1 = n1.next;
                    curr = res;
                } else {
                    curr.next = n1;
                    n1 = n1.next;
                    curr = curr.next;
                }
            } else {
                if (res == null) {
                    res = n2;
                    n2 = n2.next;
                    curr = res;
                } else {
                    curr.next = n2;
                    n2 = n2.next;
                    curr = curr.next;
                }
            }
        }
        if (n1 != null) {
            curr.next = n1;
        }
        if (n2 != null) {
            curr.next = n2;
        }
        return res;
    }
}

// time O(nlog), space O(1)
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) return null;
        int pointer = 0;
        ListNode ans = null;
        while (pointer < len && lists[pointer] == null) pointer++;
        if (pointer == len) return null;
        ans = lists[pointer];
        for (int i = pointer + 1; i < len; i++) ans = mergeTwo(ans, lists[i]);
        return ans;
    }
    private ListNode mergeTwo(ListNode n1, ListNode n2) {
        if (n1 == null) return n2;
        if (n2 == null) return n1;
        ListNode res = null;
        ListNode curr = null;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                if (res == null) {
                    res = n1;
                    n1 = n1.next;
                    curr = res;
                } else {
                    curr.next = n1;
                    n1 = n1.next;
                    curr = curr.next;
                }
            } else {
                if (res == null) {
                    res = n2;
                    n2 = n2.next;
                    curr = res;
                } else {
                    curr.next = n2;
                    n2 = n2.next;
                    curr = curr.next;
                }
            }
        }
        if (n1 != null) {
            curr.next = n1;
        }
        if (n2 != null) {
            curr.next = n2;
        }
        return res;
    }
}

// we can also use priority queue to merge all lists at the same time, with a little extra space

// time O(nlogn), space O(l)
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) return null;
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a,b) -> a.val-b.val);
        for (ListNode n : lists) if (n != null) heap.offer(n);
        ListNode ans = null;
        ListNode curr = null;
        while (!heap.isEmpty()) {
            ListNode ln = heap.poll();
            if (ans == null) {
                ans = ln;
                curr = ans;
            } else {
                curr.next = ln;
                curr = curr.next;
            }
            if (ln.next != null) heap.offer(ln.next);
        }
        return ans;
    }
}