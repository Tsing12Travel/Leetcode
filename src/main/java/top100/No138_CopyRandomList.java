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


    public Node copyRandomList2(Node head) {
        if (head == null) return null;

        Node curr = head;
        // 复制各节点并构建拼接链表
        while (curr != null) {
            Node newNode = new Node(curr.val);
            newNode.next = curr.next;
            curr.next = newNode;
            curr = newNode.next;
        }

        curr = head;
        // 构建各新节点的 random 指向
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // 拆分链表
        curr = head.next;
        Node prev = head;
        Node res = head.next;
        while (curr.next != null) {
            curr.next = curr.next.next;
            prev.next = prev.next.next;
            prev = prev.next;
            curr = curr.next;
        }

        prev.next = null;  // 原链表尾节点
        return res;  // 返回新链表头节点
    }
}
