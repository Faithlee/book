package algorithm.minstack;

import java.util.Stack;

/**
 * 最小栈
 */
public class MinStackV1 {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStackV1() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (!minStack.isEmpty()) {
            if (x <= minStack.peek()) {
                minStack.push(x);
            }
        } else {
            minStack.push(x);
        }
    }

    public void pop() {
        int pop = stack.pop();
        int top = minStack.peek();
        if (pop == top) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStackV1 v1 = new MinStackV1();
        v1.push(3);
        v1.push(5);
        v1.push(2);

        System.out.println(v1.getMin());
        v1.pop();
        System.out.println(v1.top());
        System.out.println(v1.getMin());
    }
}
