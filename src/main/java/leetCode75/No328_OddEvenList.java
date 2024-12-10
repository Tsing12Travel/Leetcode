package leetCode75;

public class No328_OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;

        // 初始化奇数和偶数的头和尾节点
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        // 遍历链表，分离奇数节点和偶数节点
        while (even != null && even.next != null) {
            odd.next = even.next;  // 连接下一个奇数节点
            odd = odd.next;  // 移动奇数指针

            even.next = odd.next;  // 连接下一个偶数节点
            even = even.next;  // 移动偶数指针
        }

        // 将奇数节点的尾与偶数节点的头相连
        odd.next = evenHead;

        return head;
    }


    public static void main(String[] args) {
        // 创建链表 [1, 2, 3, 4, 5]
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

        No328_OddEvenList No328 = new No328_OddEvenList();
        ListNode result = No328.oddEvenList(head);

        // 打印结果
        while (result != null) {
            System.out.print(result.val + " -> ");
            result = result.next;
        }
        System.out.println("null");
    }
}
