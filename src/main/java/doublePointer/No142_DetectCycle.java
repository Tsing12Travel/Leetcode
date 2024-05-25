package doublePointer;

/*
142. 环形链表 II
给定一个链表的头节点 head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
不允许修改链表。
*/


public class No142_DetectCycle {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // has cycle
            if (fast == slow) {
                break;
            }
        }

        // no cycle
        if (fast == null || fast.next == null) {
            return null;
        }

        // find head of cycle
        ListNode start = head;
        while ( start != slow) {
            slow = slow.next;
            start = start.next;
        }
        return slow;
    }
}

/*
// 链表是否存在环
boolean hasCycle(doublePointer.ListNode head) {
    // 快慢指针初始化，指向 head
    doublePointer.ListNode fast = head;
    doublePointer.ListNode slow = head;
    // 快指针走到末尾时停止
    while (fast != null && fast.next != null) {
        // 慢指针走一步，快指针走两步
        slow = slow.next;
        fast = fast.next.next;
        // 快慢指针相遇，说明有环
        if (fast == slow) {
            return true;
        }
    }
    // 不包含环
    return false;
}
*/
