package learn.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/9/13 19:13
 * @description 理解一下logback and slf4j的绑定过程
 */
public class LogbackTest {


    private static final Logger logger = LoggerFactory.getLogger(LogbackTest.class);


    public static void main(String[] args) {
        System.out.println("==打印日志==");
        logger.info("ddddd");
    }


}
