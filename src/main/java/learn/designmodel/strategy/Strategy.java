package learn.designmodel.strategy;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/9/28 11:11
 * @description
 */
public interface Strategy {

    /**
     * 记录日志
     * @param msg 需要记录的日志信息
     */
    void recordLog(String msg);

}
