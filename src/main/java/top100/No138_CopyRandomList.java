package top100;

import java.util.HashMap;
import java.util.Map;

// Definition for a Node
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class No138_CopyRandomList {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();
        Node curr = head;

        // 复制各个节点，建立 “原节点 -> 新节点” 的 map 映射
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        curr = head;
        // 构建新链表的 next 和 random 指向
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }

        // 返回新链表的头节点
        return map.get(head);
    }
}
