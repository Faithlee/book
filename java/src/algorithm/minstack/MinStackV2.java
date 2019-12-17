package algorithm.minstack;

import java.util.Stack;

/**
 * 最小栈V2
 */
public class MinStackV2 {

    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<>();

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin(){
        return min;
    }

    public static void main(String[] args) {
        MinStackV2 v2 = new MinStackV2();
        v2.push(3);
        v2.push(5);
        v2.push(2);

        System.out.println(v2.top());
        System.out.println(v2.getMin());

        v2.pop();

        System.out.println(v2.top());
        System.out.println(v2.getMin());
    }
}
