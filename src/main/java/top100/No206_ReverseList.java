package top100;

public class No206_ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public ListNode reverseList3(ListNode head) {
        return recur(head, null);
    }

    private ListNode recur(ListNode curr, ListNode prev) {
        if (curr == null) return prev;  // 终止条件

        ListNode res = recur(curr.next, prev);  // 递归后继节点
        curr.next = prev;  // 修改节点引用指向
        return res;  // 返回反转链表的头节点
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        No206_ReverseList solution = new No206_ReverseList();
        ListNode res = solution.reverseList(head);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
