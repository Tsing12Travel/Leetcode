package top100;

public class No25_ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode curr = head;
        int len = 0;

        while (curr != null) {
            len++;
            curr = curr.next;
        }

        while (len >= k) {
            ListNode start = prev.next;
            ListNode end = start;
            for (int i = 0; i < k; i++) {
                end = end.next;
            }
            prev.next = reverse(start, end);
            prev = start;
            len -= k;
        }

        return dummy.next;
    }

    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode prev = tail;

        while (head != tail) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }


    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;

        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }

            if (end == null) {
                break;
            }

            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse2(start);
            start.next = next;
            pre = start;

            end = pre;
        }
        return dummy.next;
    }

    private ListNode reverse2(ListNode start) {
        ListNode prev = null;
        ListNode curr = start;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
