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
}
