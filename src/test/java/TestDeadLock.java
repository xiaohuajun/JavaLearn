/**
 * @author Danny.
 * @version 1.0
 * @date 2019/9/18 19:02
 * @description
 */
public class TestDeadLock {


    public static void main(String[] args) {
        Object objA = new Object();
        Object objB = new Object();



        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objA) {
                    System.out.println("得到objA");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (objB) {
                        System.out.println("得到objB");
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objB) {
                    System.out.println("得到objB");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (objA) {
                        System.out.println("得到objA");
                    }
                }
            }
        }).start();
    }
}
