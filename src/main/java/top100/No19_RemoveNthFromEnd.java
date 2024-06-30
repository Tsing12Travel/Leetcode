package top100;

import java.util.Deque;
import java.util.LinkedList;

public class No19_RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode left = dummy;
        ListNode right = head;

        while (n > 0) {
            right = right.next;
            n--;
        }

        while (right != null) {
            left = left.next;
            right = right.next;
        }

        left.next = left.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode curr = dummy;
        Deque<ListNode> stack = new LinkedList<>();

        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        for (int i = 0; i < n; i++) {
            stack.pop();
        }

        ListNode prev = stack.pop();
        prev.next = prev.next.next;
        return dummy.next;
    }
}
