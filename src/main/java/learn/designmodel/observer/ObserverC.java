package learn.designmodel.observer;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/10/8 13:35
 * @description
 */
public class ObserverC implements Observer {

    private String observerCState;

    @Override
    public void update(String state) {
        //与被观察者的状态一致
        observerCState = state;
        System.out.println("ObserverB接收消息 " + state);

    }
}
