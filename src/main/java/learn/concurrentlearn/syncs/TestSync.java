package learn.concurrentlearn.syncs;




/**
 * @author Danny.
 * @version 1.0
 * @date 2019/7/23 13:46
 * @description synchronized : 考点：synchronized作用域，synchronized保证内存可见性
 * 类锁和对象锁
 * 修饰static method 是类锁（该类所有对象所共享）
 * 对象锁：修饰实例方法、代码块
 * 注意：
 * 1、某个线程获取对象锁后，该对象的其他的同步方法是锁定的，其他线程无法访问
 * 2、某个线程获取对象锁，但另外一个线程是可以访问该对象没有同步的方法或者代码块
 * 3、对象锁、类锁是相互独立的
 */
public class TestSync implements Runnable {

    int b = 100;

    synchronized void m1() throws InterruptedException {
        b = 1000;
        //6 sleep(long time) 让当前线程暂停执行一段时间,并没有释放对象锁
        Thread.sleep(500);
        System.out.println("b=" + b);
    }

    synchronized void m2() throws InterruptedException {
        //5  让当前线程暂停执行一段时间,并没有释放对象锁
        Thread.sleep(250);
        b = 2000;
    }

    public static void main(String[] args) throws InterruptedException {

        TestSync tt = new TestSync();
        //1
        Thread t = new Thread(tt);
        //2 启动线程不一定立马执行,等待cpu的调度，只不过进入了可运行状态
        t.start();
        //3
        tt.m2();
        //4
        System.out.println("main thread b = " + tt.b);

        /**
         * 1、main thread b = 2000  ;  b=1000
         * 这种情况：
         * (1)子线程 t start()后并没有立即运行，只是就绪状态；
         * 但是m1(),m2()都加了synchronize，并作用于同一对象，因此子线程t只有进入锁池状态进行等待
         * (2)这时主线程先执行并调用m2()，并赋值b=2000,然后释放对象锁,继续执行主线程main thread b = 2000
         *
         * (3)子线程 t 分配到cpu获取锁，m1()执行，b=1000
         *
         */
        //==================================
        /**
         * 2、b=1000   main thread b=1000
         * 这种情况：
         * （1）主线程分配到cpu,先执行，执行了m2(),b=2000,m2()释放锁，这个时候主线程把cpu资源让给了子线程
         * （2）这个时候子线程执行，并获取了锁，执行m1(),b=1000,
         * （3）这个时候主线程拿到cpu继续执行main thread b 并赋值1000
         *
         */

        /**
         *3、b=1000   main thread b=2000
         * （1）子线程先执行m1(),b=1000;释放对象锁
         * （2）主线程执行m2()，b=2000,
         * （3）main thread b=2000
         *
         *
         */
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
