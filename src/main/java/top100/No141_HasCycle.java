package top100;

import java.util.HashSet;
import java.util.Set;

public class No141_HasCycle {
    // 「Floyd 判圈算法」（又称龟兔赛跑算法）
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;  // 乌龟和兔子同时从起点出发
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;  // 乌龟走一步
            fast = fast.next.next;  // 兔子走两步
            if (fast == slow) return true;  // 兔子追上乌龟（套圈），说明有环
        }

        return false;  // 访问到了链表末尾，无环
    }


    public boolean hasCycle2(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }

        return false;
    }
}
