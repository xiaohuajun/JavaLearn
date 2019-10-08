package learn.designmodel.observer;


import java.util.LinkedList;
import java.util.List;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/10/8 13:10
 * @description 被观察者，状态改变，通知观察者自动改变
 * 一般有一个观察者集合
 */
public class Subject {

    /**
     * 保存观察者
     */
    private List<Observer> observers = new LinkedList<>();


    /**
     * 注册观察者
     *
     * @param observer 被注册观察者
     */
    public void attach(Observer observer) {
        observers.add(observer);
        System.out.println("add a observer->" + observer);
    }

    /**
     * 注销一个观察者
     *
     * @param observer 被注销的观察者
     */
    public void detach(Observer observer) {
        observers.remove(observer);
        System.out.println("remove a observer->" + observer);

    }

    /**
     * 通知观察者更新状态
     *
     * @param state 需要更新的状态
     */
    public void notifyObservers(String state) {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }


}
