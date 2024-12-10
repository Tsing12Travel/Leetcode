package leetCode75;

import java.util.ArrayList;
import java.util.List;

public class No2130_PairSum {
    public int pairSum(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode curr = head;

        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }

        int res = list.getFirst() + list.getLast();
        int size = list.size();

        for (int i = 0; i < size / 2; i++) {
            res = Math.max(res, list.get(i) + list.get(size - i - 1));
        }

        return res;
    }


    /*
    1.找到链表中点：使用快慢指针方法，快指针每次移动两步，慢指针每次移动一步。当快指针到达链表末尾时，慢指针正好在链表的中间。
    2.反转链表后半部分：从中点开始，反转后半部分链表，方便与前半部分链表对应节点进行计算。
    3.计算孪生和：遍历前半部分和反转后的后半部分链表，计算每对孪生节点的和，并更新最大值。
    4.返回最大孪生和：返回最大孪生和的结果。

    时间复杂度： O(n)，其中 n 是链表节点的数量。遍历链表三次：一次找到中点，一次反转链表，一次计算孪生和。
    空间复杂度： O(1)，反转链表使用常量额外空间。*/
    public int pairSum2(ListNode head) {
        if (head == null || head.next == null) {
            return 0;
        }

        // 使用快慢指针找到链表的中点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 反转后半部分链表
        ListNode prev = null, curr = slow;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        // 计算最大孪生和
        int maxTwinSum = 0;
        ListNode firstHalf = head, secondHalf = prev;
        while (secondHalf != null) {
            maxTwinSum = Math.max(maxTwinSum, firstHalf.val + secondHalf.val);
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return maxTwinSum;
    }


    public static void main(String[] args) {
        // 创建链表：5 -> 4 -> 2 -> 1
        ListNode head = new ListNode(5, new ListNode(4, new ListNode(2, new ListNode(1))));

        No2130_PairSum no2130 = new No2130_PairSum();
        System.out.println(no2130.pairSum2(head));
    }
}
