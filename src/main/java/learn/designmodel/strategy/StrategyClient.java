package learn.designmodel.strategy;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/9/28 11:20
 * @description
 */
public class StrategyClient {

    public static void main(String[] args) {
        StrategyContext sc = new StrategyContext();
        sc.writeLogs("记录日志");
        sc.writeLogs("再次记录日志");
    }
}
