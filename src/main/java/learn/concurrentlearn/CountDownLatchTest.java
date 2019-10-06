package learn.concurrentlearn;


import java.util.concurrent.*;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/7/3 11:16
 * @description
 */
public class CountDownLatchTest implements Runnable {

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    @Override
    public void run() {
        System.out.println("当前任务已经检查完闭");
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        int corePoolSize = 10;
        int maxiumPoolSize = 100;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

        //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
        ExecutorService executorServiceFix = Executors.newFixedThreadPool(14);
        //这里的线程池是无限大的，当一个线程完成任务之后，
        //这个线程可以接下来完成将要分配的任务，而不是创建一个新的线程，复用之前的线程
        ExecutorService executorServiceCache = Executors.newCachedThreadPool();
        //创建一个定长线程池，支持定时及周期性任务执行
        ExecutorService executorServiceSchdule = Executors.newScheduledThreadPool(2);
        //按顺序来执行线程任务-但是不同于单线程，这个线程池只是只能存在一个线程，这个线程死后另外一个线程会补上。
        ExecutorService executorServiceSingle = Executors.newSingleThreadExecutor();
        CountDownLatchTest demo = new CountDownLatchTest();
        for (int i = 0; i < 10; i++) {
           Future future =  executorServiceFix.submit(demo);
        }
        countDownLatch.await(10, TimeUnit.SECONDS);
        //主线程才得以往下运行
        System.out.println("===============");
        System.out.println("主线程开始运行");
        executorServiceFix.shutdown();
    }
}
