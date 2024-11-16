package leetBook;

public class No136_DeleteNode {
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;
        if (head.val == val) {
            return head.next;
        }

        ListNode curr = head;
        while (curr.next != null && curr.next.val != val) {
            curr = curr.next;
        }

        if (curr.next != null) {
            curr.next = curr.next.next;
        }

        return head;
    }
}
