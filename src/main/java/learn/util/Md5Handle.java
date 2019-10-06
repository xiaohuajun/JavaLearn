package learn.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/7/9 13:03
 * @description md5加密工具类
 */
public class Md5Handle {


    public static String md5(String sParam) {

        byte[] secretBytes;

        try {
            //获取加密摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(sParam.getBytes());
            //加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5Code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5Code.length(); i++) {
            md5Code = "0" + md5Code;
        }
        return md5Code;
    }
}
