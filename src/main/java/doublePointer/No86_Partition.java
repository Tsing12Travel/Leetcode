package doublePointer;

/*
86.分割链表
给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
你应当 保留 两个分区中每个节点的初始相对位置。
*/

// 先拆分成两个链表，一个值均小于 x，另一个均大于 x，然后再连接起来
public class No86_Partition {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 3, 2, 5, 2};
        ListNode head = new ListNode(arr);
        No86_Partition res = new No86_Partition();
        System.out.println(res.partition(head, 3));
    }

    public ListNode partition(ListNode head, int x) {
        ListNode dummyS = new ListNode(-1);
        ListNode dummyB = new ListNode(-1);
        ListNode ls = dummyS;
        ListNode lb = dummyB;
        ListNode curr = head;

        // 先拆分成两个链表，一个值均小于 x，另一个均大于 x，然后再连接起来
        while (curr != null) {
            // 注意这里的 = ，只能放在大于 x 的那个链表
            if (curr.val >= x) {
                lb.next = curr;
                lb = lb.next;
            } else {
                ls.next = curr;
                ls = ls.next;
            }
            // 不能直接用 curr = curr.next; 否则会形成环形链表，需要断链
            ListNode temp = curr.next;
            curr.next = null;
            curr = temp;
        }

        ls.next = dummyB.next;
        return dummyS.next;
    }
}
