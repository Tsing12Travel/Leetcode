package top100;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class No234_IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode curr = head;
        List<Integer> list = new ArrayList<>();

        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }

        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (list.get(left) != list.get(right)) return false;
            left++;
            right--;
        }

        return true;
    }


    public boolean isPalindrome2(ListNode head) {
        ListNode mid = findMid(head);
        ListNode midHead = reverse(mid);

        while (midHead != null) {
            if (midHead.val != head.val) return false;  // 不是回文链表

            midHead = midHead.next;
            head = head.next;
        }

        return true;
    }

    // 876.链表中间节点
    public ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 206.反转链表(此处使用迭代方式翻转，而不能使用递归，否则空间复杂度将变成 O(N)
    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        return pre;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(2);

        No234_IsPalindrome No234 = new No234_IsPalindrome();
        System.out.println(No234.isPalindrome2(head));
    }
}
