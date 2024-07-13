package top100;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class No20_IsValid {
    public boolean bracketsIsValid(String s) {
        if (s.isEmpty()) return true;

        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }


    private static final Map<Character, Character> map = new HashMap<>() {{
        put('{', '}');
        put('[', ']');
        put('(', ')');
        put('?', '?');
    }};

    public boolean bracketsIsValid2(String s) {
        if (!s.isEmpty() && !map.containsKey(s.charAt(0))) return false;

        LinkedList<Character> stack = new LinkedList<>() {{
            add('?');
        }};

        for (Character c : s.toCharArray()) {
            if (map.containsKey(c)) stack.addLast(c);
            else if (map.get(stack.removeLast()) != c) return false;
        }

        return stack.size() == 1;
    }


    public static void main(String[] args) {
        No20_IsValid test = new No20_IsValid();
        System.out.println(test.bracketsIsValid("()[]{}"));
    }
}
