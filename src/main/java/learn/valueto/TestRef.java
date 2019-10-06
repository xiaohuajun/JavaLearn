package learn.valueto;


/**
 * @author Danny.
 * @version 1.0
 * @date 2019/7/14 17:50
 * @description 理解值传递和引用传递
 */
public class TestRef {


    class Obj {
        private String str = "default value";

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }

    private Obj aObj = new Obj();


    private int anInt = 0;

    public Obj getAObj() {
        return aObj;
    }

    public int getAnInt() {
        return anInt;
    }

    public void changeObj(Obj inObj) {
        inObj.setStr("changed value");
    }

    public void changeInt(int inInt) {
        inInt = 1;
    }


    public static void main(String[] args) {
        TestRef testRef = new TestRef();
        System.out.println("******************引用类型********************");
        System.out.println("调用changeObj()前：" + testRef.getAObj());
        testRef.changeObj(testRef.getAObj());
        System.out.println("调用changeObj()后：" + testRef.getAObj());
        System.out.println("******************基本数据类型********************");
        System.out.println("调用changeInt()前：" + testRef.getAnInt());
        testRef.changeInt(testRef.getAnInt());
        System.out.println("调用changeInt()后：" + testRef.getAnInt());

    }

}
