package doublePointer;

public class MiddleNode {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        // int[] arr = new int[]{1, 2, 3, 4, 5};
        ListNode head = new ListNode(arr);
        MiddleNode middleNode = new MiddleNode();
        ListNode res = middleNode.middleNode(head);
        System.out.println(res);
    }

    // 此处使用 private 修饰词(私有，安全)，也可使用 public 或者 不写(默认 default)
    private ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}