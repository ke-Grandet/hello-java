package org.example.main.other;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程示例：生产者和消费者（本示例使用方法3）
 * 方法1：阻塞队列（BlockingQueue）
 * 方法2：synchronized、wait、notify
 * 方法3：Lock、Condition、await、signal
 */
public class ThreadExample {

    public void runExample() {
        WareHouse wareHouse = new WareHouse(5);
        Thread p1 = new Producer("P-1", 8, wareHouse);
        Thread p2 = new Producer("P-2", 17, wareHouse);
        Thread c1 = new Consumer("C1", 16, wareHouse);
        Thread c2 = new Consumer("C2", 2, wareHouse);
        Thread c3 = new Consumer("C3", 2, wareHouse);
        p1.start();
        p2.start();
        c1.start();
        c2.start();
        c3.start();
    }

    /**
     * 生产者和消费者共用的仓库
     */
    public static class WareHouse {
        public final int capacity;  // 容量上限
        private final Queue<Integer> queue = new LinkedList<>();  // 仓库库存队列
        private static final Lock lock = new ReentrantLock();  // 锁
        private static final Condition notFullCondition = lock.newCondition();  // 条件，用于生产者
        private static final Condition notEmptyCondition = lock.newCondition();  // 条件，用于消费者

        public WareHouse(int capacity) {
            this.capacity = capacity;
        }

        public void produce(int product) {
            lock.lock();  // 获得锁
            try {
                while (queue.size() == capacity) {
                    System.out.printf("生产者[%s]想生产，但仓库已满\n", Thread.currentThread().getName());
                    notFullCondition.await();  // 在该条件上等待
                }
                queue.offer(product);  // 产品加入队列
                System.out.printf("生产者[%s]生产了[%s]，库存现在是：%s\n",
                        Thread.currentThread().getName(), product, queue);
                notEmptyCondition.signal();  // 唤醒在该条件上等待的线程，即消费者
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();  // 释放锁
            }
        }

        public void consume() {
            lock.lock();  // 获得锁
            try {
                while (queue.isEmpty()) {
                    System.out.printf("消费者[%s]想消费，但仓库空了\n", Thread.currentThread().getName());
                    notEmptyCondition.await();  // 在该条件上等待
                }
                int i = queue.poll();  // 从队列中取出产品
                System.out.printf("消费者[%s]消费了[%s]，库存现在是：%s\n",
                        Thread.currentThread().getName(), i, queue);
                notFullCondition.signal();  // 唤醒在该条件上等待的线程，即生产者
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();  // 释放锁
            }
        }
    }

    /**
     * 生产者
     */
    public static class Producer extends Thread {
        private int remainTimes;  // 剩余生产次数
        private int product = 0;  // 要生产的产品
        private final WareHouse wareHouse;

        public Producer(String name, int remainTimes, WareHouse wareHouse) {
            super(name);
            this.remainTimes = remainTimes;
            this.wareHouse = wareHouse;
        }

        @Override
        public void run() {
            System.out.printf("生产者%s开始行动\n", getName());
            while (remainTimes > 0) {
                wareHouse.produce(product++);
                remainTimes--;
                try {
                    sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("生产者%s死亡\n", getName());
        }
    }

    /**
     * 消费者
     */
    public static class Consumer extends Thread {
        private int remainTimes;  // 剩余消费次数
        private final WareHouse wareHouse;

        public Consumer(String name, int remainTimes, WareHouse wareHouse) {
            super(name);
            this.remainTimes = remainTimes;
            this.wareHouse = wareHouse;
        }

        @Override
        public void run() {
            System.out.printf("消费者%s开始行动\n", getName());
            while (remainTimes > 0) {
                wareHouse.consume();
                remainTimes--;
                try {
                    sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("消费者%s死亡\n", getName());
        }
    }

}
