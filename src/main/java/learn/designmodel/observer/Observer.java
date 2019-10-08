package learn.designmodel.observer;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/10/8 13:17
 * @description 观察者：被观察者改变，观察者也跟着跟着改变
 */
public interface Observer {

    /**
     * 更新观察者的状态
     *
     * @param state 需要的改变的状态
     */
    void update(String state);

}
