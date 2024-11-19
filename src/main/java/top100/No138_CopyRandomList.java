package top100;

import java.util.HashMap;
import java.util.Map;

public class No138_CopyRandomList {
    // 普通链表的复制
    public Node copyNormalList(Node head) {
        Node cur = head;
        Node dum = new Node(0), pre = dum;
        while(cur != null) {
            Node node = new Node(cur.val); // 复制节点 cur
            pre.next = node;               // 新链表的 前驱节点 -> 当前节点
            // pre.random = "???";         // 新链表的 「 前驱节点 -> 当前节点 」 无法确定
            cur = cur.next;                // 遍历下一节点
            pre = node;                    // 保存当前新节点
        }
        return dum.next;
    }


    // 哈希表
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


    // 拼接 + 拆分
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
            //if (curr.random != null) 的目的是避免访问 null 引用，确保深拷贝过程中每个节点的 random 指针设置正确
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
