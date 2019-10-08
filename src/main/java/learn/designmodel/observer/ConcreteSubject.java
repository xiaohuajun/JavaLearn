package learn.designmodel.observer;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/10/8 13:28
 * @description 具体的被观察者--真正发出状态改变的对象
 */
public class ConcreteSubject extends Subject {

    /**
     * 观察者的状态
     */
    private String state;


    public String getState() {
        return state;
    }

    /**
     * 被观察者的改变状态的方法
     *
     * @param newState 改变的状态
     */
    public void change(String newState) {

        //准备状态
        state = newState;

        System.out.println("concrete subject is state -> " + state);

        notifyObservers(state);

    }


}
