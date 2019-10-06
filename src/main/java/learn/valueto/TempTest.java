package learn.valueto;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/7/14 18:16
 * @description
 */
public class TempTest {

    private void test1(int a) {
        a = 5;
        System.out.println("test1方法中的a=" + a);
    }

    public static void main(String[] args) {
        TempTest t = new TempTest();
        int a = 3;
        //传递后，test1方法对变量值的改变不影响这里的a
        t.test1(a);
        System.out.println("main方法中的a=" + a);
    }
}
