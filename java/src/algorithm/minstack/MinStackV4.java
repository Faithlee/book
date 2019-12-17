package algorithm.minstack;

/**
 * 最小栈：使用链表实现栈功能
 */
public class MinStackV4 {
    Node head;

    class Node {
        int value;
        int min;
        Node next;

        Node(int x, int min) {
            value = x;
            this.min = min;
            next = null;
        }
    }

    public void push(int x){
        if (null == head) {
            head = new Node(x, x);
        } else {
            Node node = new Node(x, Math.min(x, head.min));
            node.next = head;
            head = node;
        }
    }

    public void pop(){
        if (head != null) {
            head = head.next;
        }
    }

    public int top(){
        if (head != null) {
            return head.value;
        }

        return -1;
    }

    public int getMin(){
        if (head != null) {
            return head.min;
        }

        return -1;
    }

    public static void main(String[] args) {
        MinStackV4 v4 = new MinStackV4();
        v4.push(3);
        v4.push(5);
        v4.push(2);

        System.out.println(v4.top());
        System.out.println(v4.getMin());

        v4.pop();

        System.out.println(v4.top());
        System.out.println(v4.getMin());
    }
}
