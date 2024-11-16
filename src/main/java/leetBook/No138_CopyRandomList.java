package leetBook;

import java.util.HashMap;
import java.util.Map;

public class No138_CopyRandomList {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node curr = head;
        Map<Node, Node> map = new HashMap<>();
        // 3. 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        curr = head;
        // 4. 构建新链表的 next 和 random 指向
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }

        // 5. 返回新链表的头节点
        return map.get(head);
    }

    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        Node curr = head;

        // 1.复制各个节点，并构建拼接链表
        while (curr != null) {
            Node temp = new Node(curr.val);
            temp.next = curr.next;
            curr.next = temp;
            curr = temp.next;
        }

        // 2.构建各新节点的 random 指向
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next;
        }

        // 3.拆分链表
        curr = head.next;
        Node prev = head;
        Node res = head.next;
        while (curr.next != null) {
            prev.next = prev.next.next;
            curr.next = curr.next.next;
            prev = prev.next;
            curr = curr.next;
        }
        prev.next = null;  // 单独处理原链表尾节点
        return res;  // 返回新链表头节点
    }
}
