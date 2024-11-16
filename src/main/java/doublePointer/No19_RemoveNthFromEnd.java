package doublePointer;

// 19.给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点
// Definition for singly-linked list.
// class doublePointer.ListNode {
//     int val;
//     doublePointer.ListNode next;
//     doublePointer.ListNode() {}
//     doublePointer.ListNode(int val) { this.val = val; }
//     doublePointer.ListNode(int val, doublePointer.ListNode next) { this.val = val; this.next = next; }
// }

public class No19_RemoveNthFromEnd {
    public static void main(String[] args) {
        ListNode head = testList();
        // 新链表头节点
        ListNode newHead = removeNthFromEnd(head, 3);
        ListNode curr = newHead;
        System.out.println(newHead.val);
        // 遍历新的链表
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }

    public static ListNode testList() {
        // 创建链表
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        head.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        return head;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode x = findFromEnd(dummy, n + 1);
        x.next = x.next.next;
        return dummy.next;
    }

    private static ListNode findFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }

        ListNode p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }
}
