package top100;

import java.util.HashSet;
import java.util.Set;

public class No142_DetectCycle {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {  // 说明已经找到了快慢指针重合点，但不一定是入口位置
                ListNode curr = head;
                while (curr != slow) {
                    curr = curr.next;
                    slow = slow.next;
                }
                return curr;
            }
        }

        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode curr = head;

        while (curr != null) {
            if (set.contains(curr)) {
                return curr;
            }
            set.add(curr);
            curr = curr.next;
        }

        return null;
    }
}
