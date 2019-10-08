package learn.designmodel.strategy;


/**
 * @author Danny.
 * @version 1.0
 * @date 2019/9/28 11:16
 * @description 策略上下文中协调各个算法集
 * 1、支付场景-选择具体的算法
 * 2、容错场景-一个算法失败，自动转换到其他的算法执行
 * 3、消除大量的if....else的代码
 */
public class StrategyContext {


    private Strategy strategy;


    public void writeLogs(String msg) {
        try {
            strategy = new DbLogs();
            strategy.recordLog(msg);
        } catch (Exception e) {
            strategy = new FileLogs();
            strategy.recordLog(msg);
        }
    }

}
