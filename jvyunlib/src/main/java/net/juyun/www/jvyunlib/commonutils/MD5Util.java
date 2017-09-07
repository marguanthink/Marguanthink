package net.juyun.www.jvyunlib.commonutils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 关 on 2017/3/28.
 */

public class MD5Util {
    /**
     *
     */
    public static String md5Code(String psd) {
        try {
// 加盐
            psd = psd + "";
// 1，获取加密算法对象，单利设计模式
            MessageDigest instance = MessageDigest.getInstance("MD5");
// 2，通过加密算法操作，对psd进行哈希加密操作
            byte[] digest = instance.digest(psd.getBytes());
            StringBuffer sb = new StringBuffer();
// 循环16次
            for (byte b : digest) {
// 获取b的后8位
                int i = b & 0xff;
// 将10进制数，转化为16进制
                String hexString = Integer.toHexString(i);
// 容错处理，长度小于2的，自动补0
                if (hexString.length() < 2) {
                    hexString = "0" + hexString;
                }
// 把生成的32位字符串添加到stringBuffer中
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
