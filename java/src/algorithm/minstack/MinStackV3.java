package algorithm.minstack;

import java.util.Stack;

/**
 * 最小栈v3：将入栈的值与最小值作比较，栈中保存差值
 */
public class MinStackV3 {

    long min;

    Stack<Long> stack;

    public MinStackV3(){
        stack = new Stack<>();
    }

    public void push(int x){
        if (stack.isEmpty()) {
            min = x;
            stack.push(x-min);
        } else {
            stack.push(x-min);
            if (x < min) {
                min = x;
            }
        }
    }

    public void pop(){
        if (stack.isEmpty()){
            return;
        }
        long pop = stack.pop();
        if (pop < 0) {
            min = min - pop;
        }
    }

    public int top(){
        long top = stack.peek();
        if (top < 0) {
            return (int)min;
        } else {
            return (int)(top + min);
        }
    }

    public int getMin(){
        return (int) min;
    }
}
