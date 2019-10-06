package learn.basespecial;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/9/6 20:34
 * @description 内部类可以实现接口，这样可以实现多继承
 */
public class InnerClassTest {

    private static int h = 10;

    private int b = 12;

    public void testOuterClass() {
        System.out.println("外部类的方法");
    }


    public static class StaticInnerClass {
        public int a = 11;

        public void outA() {
            System.out.println(h);
        }

    }

    public class InnerClass {
        private int h = 19;

        public void testInnerClass() {
            System.out.println("this is InnerClass h=" + h);
            System.out.println("this is outerClass static h=" + InnerClassTest.h);
            System.out.println("this is outerClass static b=" + InnerClassTest.this.b);
            testOuterClass();
        }
    }

    /**
     * 匿名内部类，自定义接口
     */
    interface Listen {
        /**
         * 点击事件
         * @param obj
         */
        void onClick(Object obj);
    }

    public void noNameClassTest() {
        //创建一个匿名内部类，并实现Listen接口，并重写里面的方法
        Listen listen = new Listen() {
            int lField = 22;

            @Override
            public void onClick(Object o) {
                System.out.println("监听点击对象->" + o);
                System.out.println("自己的字段->" + lField);
                System.out.println("外部类的字段->" + b);
                System.out.println("外部类的静态字段->" + h);
            }
        };

        listen.onClick(new Object() {
            @Override
            public String toString() {
                return "obj1";
            }
        });
    }


    public static void main(String[] args) {
        //静态内部类的用法
        InnerClassTest.StaticInnerClass a = new InnerClassTest.StaticInnerClass();
        a.outA();
        //成员内部类的用法
        InnerClassTest.InnerClass innerClass = new InnerClassTest().new InnerClass();
        innerClass.testInnerClass();
        //匿名内部类的用法
        InnerClassTest in  = new InnerClassTest();
        in.noNameClassTest();

    }


}
