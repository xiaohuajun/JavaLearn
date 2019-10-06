package learn.concurrentlearn.syncs;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/7/23 17:33
 * @description
 * 1、某个线程得到了对象锁之后，该对象的其他同步方法是锁定的，其他线程是无法访问的
 * 2、某个线程得到了对象锁之后，但是另外一个线程可以访问该对象锁的非同步的方法或者代码块
 * 3、类锁和对象锁是相互独立的
 */
public class Run {


    public static void main(String[] args) {
        final TestSynchronized testSynchronized = new TestSynchronized();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                testSynchronized.minusSyn2();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                testSynchronized.minusSyn3();
            }
        });
        t1.start();
        t2.start();
    }


}
