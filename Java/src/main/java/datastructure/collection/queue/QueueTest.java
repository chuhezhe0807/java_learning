package datastructure.collection.queue;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * ClassName: QueueTest
 * Package: datastructure.collection.queue
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/11 17:18
 * @Version 1.0
 */
public class QueueTest {
    @Test
    public void test01() {
        // LinkedList 实现了Deque接口，Deque又继承于Queue
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.offer(1);// 像队列中添加一个元素
        linkedList.offer(2);
        linkedList.offer(3);

        linkedList.poll(); // 获取并移除位于队列头的元素，如果队列为空则返回null
        linkedList.remove(); // 获取并移除位于队列头的元素，如果队列为空则抛出异常
        linkedList.peek(); // 获取但不移除位于队列头部的元素，如果队列为空则返回null
        linkedList.element(); // 获取但不移除位于队列头部的元素，如果队列为空则抛出异常

        // addFirst, addLast, removeFirst, removeLast, getFirst, getLast 都在 Deque(double-ended queue 双端队列) 接口中定义
        linkedList.addFirst(0);
        linkedList.addLast(-1);
        linkedList.getFirst();
        linkedList.getLast();
    }

    // PriorityQueue 优先级队列
    // 注意：在优先级队列中，拥有最高优先级的元素最先被移除
    @Test
    public void test02() {
        // PriorityBlockingQueue 线程安全的
//        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(6, Comparator.reverseOrder());
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

//        System.out.println(queue.peek());
//        System.out.println(Arrays.toString(queue.toArray()));

        while(!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
