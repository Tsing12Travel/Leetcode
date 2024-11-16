package interview;


public class No143_ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // 1. 使用快慢指针找到链表的中点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. 反转后半部分链表
        ListNode prev = null, curr = slow, next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // 3. 合并前半部分和反转后的后半部分
        ListNode first = head, second = prev;
        while (second.next != null) {
            // 暂存前半部分和后半部分的下一个节点
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;

            // 交替连接
            first.next = second;
            second.next = temp1;

            // 移动指针
            first = temp1;
            second = temp2;
        }
    }


    public static void main(String[] args) {
        // 创建链表 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        No143_ReorderList No143 = new No143_ReorderList();
        No143.reorderList(head);

        // 打印重排后的链表，应该是 1 -> 5 -> 2 -> 4 -> 3
        ListNode node = head;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}
