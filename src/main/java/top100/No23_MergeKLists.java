package top100;

import java.util.Comparator;
import java.util.PriorityQueue;

public class No23_MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> {return o1.val - o2.val;});

        for (ListNode list : lists) {
            if (list != null) pq.offer(list);
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            curr.next = node;
            curr = curr.next;
            node = node.next;
            if (node != null) pq.offer(node);
        }

        return dummy.next;
    }


    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ListNode ans = null;
        for (ListNode node : lists) {
            ans = mergeTwoLists(ans, node);
        }

        return ans;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = l1 == null ? l2 : l1;
        return dummy.next;
    }


    public ListNode mergeKLists3(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        if (left > right) return null;

        int mid = (right + left) >> 1;
        return mergeTwoLists(merge(lists, left, mid), merge(lists, mid + 1, right));
    }


    public static void main(String[] args) {
        No23_MergeKLists No23 = new No23_MergeKLists();

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(6);
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(4);

        ListNode[] lists = new ListNode[]{l1, l2, l3};
        ListNode res = No23.mergeKLists(lists);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
