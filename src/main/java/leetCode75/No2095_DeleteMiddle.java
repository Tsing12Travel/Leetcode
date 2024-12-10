package leetCode75;

/*
2095. 删除链表的中间节点
给你一个链表的头节点 head 。删除 链表的 中间节点 ，并返回修改后的链表的头节点 head 。
长度为 n 链表的中间节点是从头数起第 ⌊n / 2⌋ 个节点（下标从 0 开始），其中 ⌊x⌋ 表示小于或等于 x 的最大整数。
对于 n = 1、2、3、4 和 5 的情况，中间节点的下标分别是 0、1、1、2 和 2 。
*/

public class No2095_DeleteMiddle {
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }


    public ListNode deleteMiddle2(ListNode head) {
        // 如果链表只有一个节点，直接返回 null
        if (head == null || head.next == null) {
            return null;
        }

        // 第一次遍历，计算链表长度
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // 找到中间节点的前一个节点
        int middleIndex = length / 2;
        current = head;
        for (int i = 0; i < middleIndex - 1; i++) {
            current = current.next;
        }

        // 删除中间节点
        current.next = current.next.next;

        return head;
    }


    public static void main(String[] args) {
        No2095_DeleteMiddle No2095 = new No2095_DeleteMiddle();

        // 创建链表 [1, 2, 3, 4, 5]
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

        // 删除中间节点
        head = No2095.deleteMiddle(head);

        // 打印修改后的链表
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        // 输出：1 2 4 5
    }
}
