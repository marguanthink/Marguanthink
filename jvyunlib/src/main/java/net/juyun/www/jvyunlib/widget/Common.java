package net.juyun.www.jvyunlib.widget;

import android.support.annotation.DrawableRes;

import net.juyun.www.jvyunlib.util.GlideImageManager;


/**
 * Created by DUANLU on 2017/3/15  12:16.
 *
 * @author DUANLU
 * @version 1.0.0
 * @class Common
 * @describe module初始化操作类
 */
public class Common {

    /**
     * 初始化，主要是设置默认图片
     *
     * @param defaultImg       默认图片
     * @param defaultCircleImg 默认圆形图片
     */
    public static void init(@DrawableRes int defaultImg, @DrawableRes int defaultCircleImg) {
        init(defaultImg, defaultImg, defaultCircleImg, defaultCircleImg);
    }

    /**
     * 初始化，主要是设置默认图片
     *
     * @param defaultImg          默认图片
     * @param defaultErrImg       默认错误图片
     * @param defaultCircleImg    默认圆形图片
     * @param defaultCircleErrImg 默认圆形错误图片
     */
    public static void init(@DrawableRes int defaultImg, @DrawableRes int defaultErrImg,
                            @DrawableRes int defaultCircleImg, @DrawableRes int defaultCircleErrImg) {
        GlideImageManager.default_img = defaultImg;
        GlideImageManager.default_err_img = defaultErrImg;
        GlideImageManager.default_circle_img = defaultCircleImg;
        GlideImageManager.default_circle_err_img = defaultCircleErrImg;
    }

}
