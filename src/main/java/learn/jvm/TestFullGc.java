package learn.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:+UseSerialGC -Xms200M Xmx200M -Xmn32m -XX:SurvivorRatio=8
 * -XX:+PrintGCDetails
 *
 * @author Danny.
 * @version 1.0
 * @date 2019/9/5 19:07
 * @description
 */
public class TestFullGc {
    private static final int _1MB = 1024 * 1024;

    /**
     * -XX:+UseSerialGC -Xms200M Xmx200M -Xmn32m -XX:SurvivorRatio=8
     * -XX:+PrintGCDetails
     *
     * @param args
     */
    public static void main(String[] args) {
        testAllocation();
    }

    private static void createBigObject(int n) {
        List<byte[]> bytesList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            bytesList.add(new byte[1024 * 1024 * 10]);
        }
        if (bytesList.size() < 20) {
            throw new RuntimeException("this is test");
        }
    }

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    @SuppressWarnings("unused")
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        // 出现一次Minor GC
        allocation4 = new byte[4 * _1MB];
    }


}
