package net.juyun.www.jvyunlib.basebean;

import java.io.Serializable;

/**
 * des:封装服务器返回数据
 * Created by xsf
 * on 2016.09.9:47
 */
public class BaseRespose <T> implements Serializable {
    public int rspCode;
    public String rspDesc;
    public T data;

    public boolean success() {
        return 1000==rspCode;
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "rspCode=" + rspCode +
                ", rspDesc='" + rspDesc + '\'' +
                ", data=" + data +
                '}';
    }
    public String info(){
        return rspDesc+"----"+rspDesc;
    }
}
