package learn.closure;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/8/15 23:54
 * @description 闭包：在一个函数中，能访问外部的变量并且当外部的变量改变后，会影响函数内计算（逻辑）
 * 成员
 */
public class Closure {

    private int length = 0;

    public ILog logger(int level){
        int logLevel = level+1;
        switch(level){
            case 1:
                //匿名内部类
                return msg -> {
                    length=msg.length();
                    System.out.println("InfoLog "+length);
                    System.out.println(logLevel);
                };
             default:
                 return msg -> {
                     length = msg.length();
                     System.out.println("ErrorLog " + length);
                     System.out.println(logLevel);
                 };
        }
    }

    public static void main(String[] args) {
        Closure closure = new Closure();
        closure.logger(1).write("xiaohuajun");
    }



}
