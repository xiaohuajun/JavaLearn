import org.junit.Test;

import java.util.*;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/7/2 21:59
 * @description 比较ArrayList和 LinkedList
 */
public class TestList {


    @Test
    public void sortOne() {

        StringTokenizer s = new StringTokenizer("");
        int[][] ta = new int[3][4];
        int[][] ta1 = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        System.out.println(ta1[0][1]);
    }

    @Test
    public void testArrayMap() {
        String str = "k1=v1;k2=v2\nA=XXX";
        Object[] objects = load(str);
        System.out.println(store(objects));
    }
    //约定：每个字典的key,value不能为等号，换行，分号

    /**
     * 把数组保存到一个字符串中
     *
     * @param strings 需要处理的字符串
     * @return 处理后的字符串
     */
    private String store(Object[] strings) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            Map<String, String> map = (Map<String, String>) strings[i];
            int j = 0;
            for (String key : map.keySet()) {
                str.append(key).append("=").append(map.get(key));
                if (j < map.size() - 1) {
                    str.append(";");
                }
                j++;
            }
            if (i < strings.length - 1) {
                str.append("\\n");
            }
        }
        return str.toString();
    }

    /**
     * 把字符串中的内容读取为数组
     *
     * @param str 需要处理的字符串
     * @return 数组
     */
    private Object[] load(String str) {
        List<Map<String, String>> mapList = new LinkedList<>();
        String[] maps = splitString(str, "\n");
        for (int i = 0; i < maps.length; i++) {
            Map<String, String> map = new LinkedHashMap<>();
            String[] keyValues = splitString(maps[i], ";");
            for (int j = 0; j < keyValues.length; j++) {
                String[] keyValue = splitString(keyValues[j], "=");
                if (keyValue.length == 2) {
                    map.put(keyValue[0], keyValue[1]);
                }
            }
            mapList.add(map);
        }
        return mapList.toArray();
    }

    /**
     * 分割字符串
     *
     * @param sourceStr  需要分割的字符串
     * @param splitRegex 分割标识
     * @return 分割后的字符数组
     */
    private String[] splitString(String sourceStr, String splitRegex) {
        if (sourceStr == null || sourceStr.trim().equals("")
                || splitRegex == null || (splitRegex.length() == 1 &&
                ".$|()[{^?*+\\".contains(splitRegex))) {
            return new String[]{};
        }
        int splitLen = splitRegex.length();
        int index;
        List<String> stringList = new ArrayList<>();
        while ((index = sourceStr.indexOf(splitRegex)) != -1) {
            stringList.add(sourceStr.substring(0, index));
            sourceStr = sourceStr.substring(index + splitLen);
        }
        if (!sourceStr.equals("")) {
            stringList.add(sourceStr);
        }
        String[] stringArray = new String[stringList.size()];
        return stringList.toArray(stringArray);
    }


    @Test
    public void testA() {
        String s = "this;is;a;str";
        try {
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
