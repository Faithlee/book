package Collection08.List;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class QueueDemo {

    public static void main(String[] args)
    {
        // todo 模拟队列数据结构，先进先出的容器

        // PriorityQueue
        priorityQueueTest();

        // stack
        stackTest();

        // queue
        dequeTest();
    }

    /**
     * 队列实现类
     */
    public static void priorityQueueTest()
    {
        // 常见操作：add/offer、peek/element、poll/remove

        // todo PrioriryQueue会对元素进行排序(自然、定制)，不是绝对标准的队列
        // todo 此队列的遍历输出行为与出队行为不一致，原因是遍历时使用toString展示，入队排序时按从小到大
        // todo 不允许null入队
        PriorityQueue queue = new PriorityQueue();

        queue.offer(6);
        queue.offer(-3);
        queue.offer(20);
        queue.offer(8);

        //System.out.println(queue);
        queue.forEach(ele -> System.out.println("输出顺序：" + ele));

        // 出队时从小到大的顺序
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    /**
     * 双端队列当栈使用
     */
    public static void stackTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        // todo 允许队列两端操作，也允许当作栈使用(pop/push)
        // todo 底层实现同样是动态的可分配的objects[]数组存储集合元素

        ArrayDeque stack = new ArrayDeque();

        stack.push("疯狂Java讲义");
        stack.push("轻量级JavaEE实战");
        stack.push("疯狂Android讲义");

        System.out.println(stack);

        System.out.println("栈顶元素：" + stack.peek());
        System.out.println("栈元素：" + stack);

        // 出栈
        System.out.println("出栈操作：" + stack.pop());
        System.out.println("栈元素：" + stack);
    }

    /**
     * 双端队列：队列
     */
    public static void dequeTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        // todo 允许队列两端操作，也允许当作栈使用(pop/push)
        ArrayDeque queue = new ArrayDeque();

        queue.offer("疯狂Java讲义");
        queue.offer("轻量级JavaEE企业级应用实战");
        queue.offer("疯狂Android讲义");

        System.out.println("队列元素：" + queue);

        System.out.println("队列头部元素：" + queue.peek());
        System.out.println("队列头部元素：" + queue.peekFirst());
        System.out.println("队列尾部元素：" + queue.peekLast());

        // 出队操作
        System.out.println("队列元素出队：" + queue.poll());
        System.out.println("队列头部元素出队：" + queue.pollFirst());
        System.out.println("队列尾部元素出队：" + queue.pollLast());
    }
}
