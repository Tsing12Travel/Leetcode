package top100;

import java.util.Stack;

class MinStack {
    private final Stack<Integer> stack;
    private final Stack<Integer> min_stack;

    public MinStack() {
        stack = new Stack<>();
        min_stack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (min_stack.isEmpty() || val <= min_stack.peek()) {
            min_stack.push(val);
        }
    }

    public void pop() {
        if (stack.pop().equals(min_stack.peek())) {
            min_stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min_stack.peek();
    }
}


// 加上判空处理，两个栈不同步的实现方法
class MinStack2 {
    private final Stack<Integer> data_stack;
    private final Stack<Integer> min_stack;

    public MinStack2() {
        data_stack = new Stack<>();
        min_stack = new Stack<>();
    }

    public void push(int val) {
        data_stack.push(val);
        if (min_stack.isEmpty() || val <= min_stack.peek()) {
            min_stack.push(val);
        }
    }

    public void pop() {
        if (data_stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        if (data_stack.pop().equals(min_stack.peek())) {
            min_stack.pop();
        }
    }

    public int top() {
        if (data_stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return data_stack.peek();
    }

    public int getMin() {
        if (min_stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return min_stack.peek();
    }
}


// 两个栈同步去实现
class MinStack3 {
    private final Stack<Integer> data_stack;
    private final Stack<Integer> min_stack;

    public MinStack3() {
        data_stack = new Stack<>();
        min_stack = new Stack<>();
    }

    public void push(int val) {
        data_stack.push(val);
        if (min_stack.isEmpty() || val <= min_stack.peek()) {
            min_stack.push(val);
        } else {
            min_stack.push(min_stack.peek());
        }
    }

    public void pop() {
        if (data_stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        data_stack.pop();
        min_stack.pop();
    }

    public int top() {
        if (data_stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return data_stack.peek();
    }

    public int getMin() {
        if (min_stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return min_stack.peek();
    }
}