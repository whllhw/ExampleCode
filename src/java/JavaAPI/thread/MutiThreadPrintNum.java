package JavaAPI.thread;

import java.util.concurrent.Semaphore;

public class MutiThreadPrintNum {

    private static volatile int num;

    /**
     * 实现多个线程同时顺序打印数值。
     * 但是存在唤醒数量太大的问题。
     */
    public static void implementsWithNotifyAll() throws InterruptedException {
        final int n = 30;
        Thread[] threads = new Thread[n];
        Object lock = new Object();
        for (int i = 0; i < n; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                while (true) {
                    synchronized (lock) {
                        if (num > 100) {
                            break;
                        }
                        // 暴力判定是否是当前线程该打印
                        if (num % n == finalI) {
                            System.out.println(String.format("%s:%d", Thread.currentThread().getName(), num));
                            num++;
                            // 暴力唤醒等待的所有线程
                            // 可以优化
                            lock.notifyAll();
                        } else {
                            // 不是当前线程该打印的，wait，释放锁
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }, String.format("thread-%d", i));
        }
        for (int i = 0; i < n; i++) {
            threads[i].start();
        }
        for (int i = 0; i < n; i++) {
            threads[i].join();
        }
    }

    /**
     * Semaphore实现线程信号量。
     */
    public static void implementsWithSemaphore() throws InterruptedException {
        int N = 30;
        Semaphore[] semaphores = new Semaphore[N];
        Thread[] threads = new Thread[N];
        // 每个线程拥有一个信号量
        for (int i = 0; i < N; i++) {
            semaphores[i] = new Semaphore(1);
            // 初始化时只有最后一个信号是可用的
            // 这样第一个线程就直接运行了
            if (i != N - 1) {
                semaphores[i].acquire();
            }
        }
        Object lock = new Object();
        for (int i = 0; i < N; i++) {
            // 拥有上一个线程的锁
            final Semaphore lastSemaphores = semaphores[(i + N - 1) % N];
            final Semaphore curSemaphores = semaphores[i];
            int finalI = i;
            threads[i] = new Thread(() -> {
                while (true) {
                    try {
                        // 拥有上一个线程的锁，可以打印
                        lastSemaphores.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (num > 100) {
                        break;
                    }
                    System.out.println(String.format("thread-%d:%d", finalI, num++));
                    // 释放当前线程的锁，让下一个线程获取到锁
                    curSemaphores.release();
                }
                curSemaphores.release();
            });
        }
        for (int i = 0; i < N; i++) {
            threads[i].start();
        }
        for (int i = 0; i < N; i++) {
            threads[i].join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        implementsWithSemaphore();
    }
}
