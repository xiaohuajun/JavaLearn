package learn.designmodel.proxy;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/9/4 00:24
 * @description
 */
public class PersonProxy implements Person {

    private Person target;

    public Person getTarget() {
        return target;
    }

    public PersonProxy setTarget(Person target) {
        this.target = target;
        return this;
    }

    @Override
    public void say() {
        if (target != null) {
            System.out.println("man say = " + System.currentTimeMillis());
            target.say();
        }
    }
}
