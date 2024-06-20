package leetBook;

import java.util.HashMap;
import java.util.Map;

public class No138_CopyRandomList {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node curr = head;
        Map<Node, Node> map = new HashMap<Node, Node>();
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
}
