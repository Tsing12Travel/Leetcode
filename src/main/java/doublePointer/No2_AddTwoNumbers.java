package doublePointer;

public class No2_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 进位
        int carry = 0;
        // 虚拟头节点
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // l1 或 l2 非空时，对位相加
        while (l1 != null || l2 != null) {
            // l1 非空时，取其值(用于后续相加)；为空时，赋值为 0
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            // 创建节点
            curr.next = new ListNode(sum % 10);
            // 新链表节点向后移动
            curr = curr.next;

            // l1 链表非空时，l1 节点向后移动
            l1 = l1 == null ? null : l1.next;
            // l1 链表非空时，l1 节点向后移动
            l2 = l2 == null ? null : l2.next;
        }

        // 重要！最高位相加时，如果有进位，需要创建新节点
        if (carry > 0) curr.next = new ListNode(carry);

        return dummy.next;
    }


    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }

    /**
     * 返回两个链表相加的头部
     */
    public ListNode add(ListNode l1, ListNode l2, int bit) {
        if (l1 == null && l2 == null && bit == 0) {
            return null;
        }

        int val = bit;

        if (l1 != null) {
            val += l1.val;
            l1 = l1.next;
        }

        if (l2 != null) {
            val += l2.val;
            l2 = l2.next;
        }

        ListNode node = new ListNode(val % 10);
        node.next = add(l1, l2, val / 10);

        return node;
    }
}
