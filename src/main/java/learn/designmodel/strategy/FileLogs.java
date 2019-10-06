package learn.designmodel.strategy;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/9/28 11:14
 * @description
 */
public class FileLogs implements Strategy {


    @Override
    public void recordLog(String msg) {
        System.out.println("write log--" + msg + "--to file");
    }
}
