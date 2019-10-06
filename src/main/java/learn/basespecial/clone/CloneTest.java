package learn.basespecial.clone;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/8/28 21:54
 * @description 对象的克隆：深复制和浅复制
 * 浅复制：对当前对象的复制，不考虑所引用的对象
 * 深复制：当前对象、其所引用的对象
 */
public class CloneTest implements Cloneable {

    private Date birth = new Date();

    private int anInt = 0;

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public void changeInt() {
        this.anInt = 1;
    }

    @Override
    public Object clone() {
        //Object obj = null;
        CloneTest obj = null;
        //可以使用线程安全的集合
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        //线程不安全的集合. 实现线程安全
        List<String> stringList = Collections.synchronizedList(new ArrayList<>());
        stringList.add("1");
        List<Integer> integers = Collections.synchronizedList(new LinkedList<>());
        //浅复制
        try {
            obj = (CloneTest) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        //深复制
        obj.birth = (Date) obj.getBirth().clone();

        return obj;
    }


    public static void main(String[] args) {
        CloneTest cloneTest = new CloneTest();
        CloneTest cloneTestB = (CloneTest) cloneTest.clone();
        //cloneTestB.changeInt();
        cloneTestB.setAnInt(10);
        System.out.println("cloneTest: " + cloneTest.getAnInt());
        System.out.println("cloneTestB: " + cloneTestB.getAnInt());
    }

}
