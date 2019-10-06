package learn.classsload;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/8/7 14:35
 * @description 类的加载步骤——
 * 一、加载：文件到内存的过程，根据类的完全限定名查找此类的字节码，并利用字节码文件创建一个Class对象
 * 二、链接：
 * 1、验证：验证文件内容，确保Class文件符合当前虚拟机的要求；不危害虚拟机的自身安全，包括
 * 文件格式、元数据、字节码、符号引用等验证，
 * 2、准备：为static变量分配内存并设置初始值（null、0）
 * 3、解析：解析接口，字段，方法：主要将常量池中的符号引用替换为直接引用
 * 三、初始化：主要完成static代码块的执行和static变量的赋值（赋值是代码中的具体值），
 * 只有对类的主动使用时，才会进行初始化，触发初始化的条件：
 * 1、创建对象实例
 * 2、访问类的静态方法和静态变量
 * 3、Class.forName() 反射类的时候
 * 4、某个子类被初始化
 */
public class StaticLoadTest {

    /**
     * a 在二中的准备阶段进行内存分配和设置初始值，初始化阶段设置代码中的具体值
     */
    public static int a = 0;

    //静态代码块；在类加载中的初始化阶段执行
    static {
        System.out.println("this is a static block  " + a);
    }

    //实例初始化快
    {
        System.out.println("this is a initialization block:" + a);
    }

    /**
     * 构造方法
     */
    public StaticLoadTest() {

        System.out.println("this is a constructor");
        a += 1;
    }

    public static void print() {

        System.out.println("this is a static method");
    }


    public static void main(String[] args) {
        //如果不创建对象实例，实例块和构造方法不会执行
        StaticLoadTest staticLoadTest = new StaticLoadTest();
        StaticLoadTest.print();

        //反射--相当于new StaticLoadTest()创建实例
        /*try {
            Class<?> c = Class.forName("interviewproblem.classsload.StaticLoadTest");
            Object o = c.newInstance();
            StaticLoadTest s = (StaticLoadTest) o;
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }

}
