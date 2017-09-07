package net.juyun.www.jvyunlib.util;

import java.io.File;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/8/18/018.
 * //照片上传工具
 */

public class MultipartBodyUtil {
    public static MultipartBody buildMultipartBody(Map<String, String> map, File[] files, String[] formFiledName) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        //往MultipartBuilder对象中添加普通input控件内容
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                //添加普通input块的数据
                builder
                        .addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\"")
                                , RequestBody.create(null, entry.getValue()));

            }
        }
        if (files != null && formFiledName != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String fileName = file.getName();
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addPart(Headers.of("Content-Disposition",
                        "form-data; name=\"" + formFiledName[i] + "\"; filename=\"" + fileName + "\""), requestBody);
            }
        }
        return builder.build();
    }
}
