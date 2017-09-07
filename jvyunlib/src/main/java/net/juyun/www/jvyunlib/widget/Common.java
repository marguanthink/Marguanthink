package net.juyun.www.jvyunlib.widget;

import android.support.annotation.DrawableRes;

import net.juyun.www.jvyunlib.util.GlideImageManager;


/**
 */
public class Common {


    public static void init(@DrawableRes int defaultImg, @DrawableRes int defaultCircleImg) {
        init(defaultImg, defaultImg, defaultCircleImg, defaultCircleImg);
    }


    public static void init(@DrawableRes int defaultImg, @DrawableRes int defaultErrImg,
                            @DrawableRes int defaultCircleImg, @DrawableRes int defaultCircleErrImg) {
        GlideImageManager.default_img = defaultImg;
        GlideImageManager.default_err_img = defaultErrImg;
        GlideImageManager.default_circle_img = defaultCircleImg;
        GlideImageManager.default_circle_err_img = defaultCircleErrImg;
    }

}
