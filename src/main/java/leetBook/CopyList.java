package leetBook;

public class CopyList {
    public Node copyList(Node head) {
        if (head == null) return null;

        Node dummy = new Node(0);
        Node prev = dummy;
        Node curr = head;

        while (curr != null) {
            Node node = new Node(curr.val);  // 复制节点 curr
            prev.next = node;  // 新链表的 前驱节点 -> 当前节点
            curr = curr.next;  // 遍历下一节点
            prev = node;  // 保存当前新节点
        }

        return dummy.next;
    }
}
