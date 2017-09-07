package net.juyun.www.jvyunlib.commonutils;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 关 on 2017/4/6.
 */

public class MD5Params {
    public static Map<String, String> setNewParams(Map<String, String> params) {
        String ymdhmss = DateUtils.getYMDHMSS();
        String YMDHMSS = DateUtils.getFromYMDHMSS();
        Map<String, String> mapNotEmptyParams = new HashMap<>();//没有空参数的
        mapNotEmptyParams.put("sequence", ymdhmss);
        mapNotEmptyParams.put("timestamp", YMDHMSS);


        Map<String, String> map = params;//新的参数列表
        map.put("sequence", ymdhmss);
        map.put("timestamp", YMDHMSS);
        map.put("version","1.0.0");
        List<String> keyList = new ArrayList<>();//用来存储没有空参数的key
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        //获取有参数的键值对
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            String key = entry.getKey();//获取键
            String val = entry.getValue();//获取值
            if (!TextUtils.isEmpty(val)) {
                mapNotEmptyParams.put(key, val);//保存新的键值对
                keyList.add(key);//保存键
            }
        }
        //排序
        String[] str = new String[keyList.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = keyList.get(i);
        }
        Arrays.sort(str);
        //拼接  获取MD5
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            stringBuffer.append(str[i]).append("=").append(mapNotEmptyParams.get(str[i])).append("&");
        }
        stringBuffer.append("key").append("=").append("j4czd11abpvtpl2b4lkwm58025pjj9vk");
        Log.i("string",stringBuffer.toString());
          //String signature = Md5Utility.getStringMD5(stringBuffer.toString());
        String signature = MD5Util.md5Code(stringBuffer.toString());

        map.put("signature", signature.toUpperCase());
        Log.i("---------",signature.toUpperCase());
        return map;
    }
}
