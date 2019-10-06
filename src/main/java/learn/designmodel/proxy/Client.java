package learn.designmodel.proxy;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/9/4 00:22
 * @description
 */
public class Client {


    public static void main(String[] args) {
        Person person = new PersonProxy();
        person.say();
    }

}
