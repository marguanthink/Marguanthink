package net.juyun.www.jvyunlib.util;

import android.os.Environment;

/**
 * Created by wy on 2015/12/10.
 * 静态常量类
 */
public class Constants {
    /**
     * 文件夹路径
     */
    public static final String ROOT_PATH = "/LIUDA";
    public static final String IMAGE_PATH = ROOT_PATH + "/IMAGE/";
    public static final String DOWNLOAD_PATH = ROOT_PATH + "/DOWNLOAD/";
    public static final String CHOOSE_PHOTO = Environment.getExternalStorageDirectory() + Constants.IMAGE_PATH + "liudaUserIcon.jpg";
    public static final String CUT_PHOTO = Environment.getExternalStorageDirectory() + Constants.IMAGE_PATH + "liudaUserCutIcon.jpg";
    /**
     * 请求码
     */
    public static final String ORDER_ID = "orderId";//订单id
    public static final int CODE_201 = 201;
    public static final int CODE_202 = 202;
    public static final int CODE_203 = 203;
    public static final int CODE_204 = 204;

    public static final int CODE_500 = 500;
    public static final int CODE_501 = 501;
    public static final int CODE_502 = 502;
    public static final int CODE_503 = 503;

    /**
     * 账号密码的存储
     */
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String ADDRESS_FLAG = "address";
    /**
     * 传递昵称
     */
    public static final String PUT_NIKE_NAME = "put_nike_name";
    public static final String RET_NIKE_NAME = "ret_nike_name";
    /**
     * 传递地址
     */

    public static final int CODE_REQUEST_CAMERA = 504;

    public static final int CODE_REQUEST_GALLERY = 505;

    /**
     * 关于降序 升序  价格排序  评价排序
     */
    public static final String DESC = "desc";
    public static final String ASC = "asc";
    public static final String PRICE = "price";
    public static final String SCORE = "score";
    public static final String SALES = "shopSales";

    /**
     * intent Extra数据的key
     */
    public static final String PRODUCT_ID = "productid";//作品id
    public static final String DESIGNER_ID = "DESIGNER_ID";//设计师id
    public static final String FIRST_CLASSIFY_ID = "FIRST_CLASSIFY_ID";//一级分类id
    public static final String GOODS_ID = "GOODS_ID";//商品id
    public static final String STORE_ID = "STORE_ID";//店铺id
    public static final String DESIGNER_NAME = "DESIGNER_NAME";//设计师名字


    public static final String LEAD_KEY = "lead_key";//是不是第一次加载引导页
    public static final String RECOMMEND_CLUB = "recommend_club";//是不是第一次进入显示推荐俱乐部

    public static final int PAGE_SIZE = 10;

}
