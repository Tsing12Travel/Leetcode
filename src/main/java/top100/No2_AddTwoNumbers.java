package top100;

public class No2_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null || l2 != null || carry != 0) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        // 所有数加完之后需要判断是否产生进位，如果有进位，则需要新建一个 ListNode(1)。
        // 此处等价于在 while 中加上 carry != 0  =>  l1、l2 为 null 但 carry ！= 0 时，循环中会创建 ListNode(1)
        // if (carry > 0) curr.next = new ListNode(carry);
        return dummy.next;
    }


    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while (l1 != null || l2 != null) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }

            curr.next = new ListNode(carry);
            curr = curr.next;
            carry /= 10;
        }

        if (carry > 0) curr.next = new ListNode(carry);
        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListNode x1 = new ListNode(1);
        ListNode x2 = new ListNode(2);
        ListNode x3 = new ListNode(3);
        ListNode x4 = new ListNode(4);
        ListNode x5 = new ListNode(5);
        x1.next = x2;
        x2.next = x3;
        x3.next = x4;
        x4.next = x5;

        No2_AddTwoNumbers No2 = new No2_AddTwoNumbers();
        ListNode result = No2.addTwoNumbers(l1, x1);
        // 输出结果
        System.out.print("Result: ");
        printList(result);
    }


    // 辅助方法：打印链表
    private static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(" -> ");
            }
            node = node.next;
        }
        System.out.println();
    }
}
