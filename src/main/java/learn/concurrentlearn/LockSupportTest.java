package learn.concurrentlearn;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/8/17 13:46
 * @description
 */
public class LockSupportTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            //park()-子线程被阻塞,parkUntil(long deadline)-阻塞指定时间
            //parkNanos 阻塞指定纳秒（1ns = 10 ^ -9）
            LockSupport.parkNanos(10000);
            //唤醒之后的动作
            System.out.println(Thread.currentThread().getName() + "-被唤醒");
        });
        t.start();
        try {
            //主线程睡眠
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //唤醒指定线程
        //LockSupport.unpark(t);
    }
}
