package learn.designmodel.strategy;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/9/28 11:14
 * @description
 */
public class DbLogs implements Strategy {

    private static final int LOG_MAX_SIZE = 5;

    @Override
    public void recordLog(String msg) {
        if (msg != null && msg.trim().length() < LOG_MAX_SIZE) {
                int a = 5 / 0;
        }
        System.out.println("write log--" + msg + "--to db");
    }
}
