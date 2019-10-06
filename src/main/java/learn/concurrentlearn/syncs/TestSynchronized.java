package learn.concurrentlearn.syncs;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/7/23 17:05
 * @description synchronized的用法以及作用域
 */
public class TestSynchronized {


    /**
     * 作用域实例方法上：锁定当前对象
     */
    public synchronized void test() {
        //todo
    }

    /**
     * 作用在代码块上：加入传入的参数是this,那就锁定当前对象，如果不是this ，那就
     * 锁定传入参数所属的对象
     */
    public void test2() {
        synchronized (this) {
            //todo
        }
    }

    /**
     * 作用在静态方法上，作用于整个类：类锁
     */
    public static synchronized void minuStatic() {
        int count = 5;
        for (int i = 0; i < 5; i++) {
            count--;
            System.out.println(Thread.currentThread().getName() + "-" + count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void minusSyn2() {
        int count = 5;
        for (int i = 0; i < 5; i++) {
            count--;
            System.out.println(Thread.currentThread().getName() + "-" + count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void minusSyn3() {
        int count = 5;
        for (int i = 0; i < 5; i++) {
            count--;
            System.out.println(Thread.currentThread().getName() + "-" + count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void minusCommon() {
        int count = 5;
        for (int i = 0; i < 5; i++) {
            count--;
            System.out.println(Thread.currentThread().getName() + "-" + count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
