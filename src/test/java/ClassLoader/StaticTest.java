package ClassLoader;


/**
 * @author Danny.
 * @version 1.0
 * @date 2019/8/8 23:32
 * @description
 */
public class StaticTest {

    public static void main(String[] args) {
        staticFunction();
    }

    //静态变量在链接中的准备阶段就赋值为 st=null
    static StaticTest st = new StaticTest();

    //3
    static {
        System.out.println("1");
    }

    // 1
    {
        System.out.println("2");
    }

    //2
    StaticTest() {
        System.out.println("3");
        System.out.println("a=" + a + "；b=" + b);
    }

    //4
    private static void staticFunction() {
        System.out.println("4");
    }

    //这是常量，在链接中的解析阶段符号引用替换为直接引用
    int a = 110;
    //静态变量在链接中的准备阶段就赋值为 b=0
    static int b = 120;

}
