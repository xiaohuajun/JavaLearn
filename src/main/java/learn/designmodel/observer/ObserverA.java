package learn.designmodel.observer;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/10/8 13:34
 * @description 具体的观察者
 */
public class ObserverA implements Observer {

    /**
     * 观察者的状态
     */
    private String observerAState;

    @Override
    public void update(String state) {
        //更新观察者的状态与被观察者一致
        observerAState = state;
        System.out.println("ObserverA接收消息 " + state);
    }
}
