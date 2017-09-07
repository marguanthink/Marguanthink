package net.juyun.www.jvyunlib.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.io.File;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**


 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class GlideImageManager extends BaseUtils {
    public static int default_img;
    public static int default_err_img;
    public static int default_circle_img;
    public static int default_circle_err_img;
    public static final int NOT_THUMBNAIL = -1;//不使用缩略图标记

    /**
     * 加载图片
     *


     */
    public static void loadImage(String url, ImageView iv) {
        Glide.with(iv.getContext()).load(url)
                .thumbnail(0.1f)//先加载原图的十分之一作为缩略图，再加载原图
                .crossFade()//淡入显示,注意:如果设置了这个,则必须要去掉asBitmap
                .placeholder(default_img)
                .error(default_err_img)
                .dontAnimate()
                .into(iv);
    }

    /**
     * 加载设置优先级的图片
     *

     */
    public static void loadImage(String url, ImageView iv, Priority priority) {
        Glide.with(iv.getContext()).load(url)
                .crossFade()//淡入显示,注意:如果设置了这个,则必须要去掉asBitmap
                .priority(priority)//优先级
                .placeholder(default_img)
                .error(default_err_img)
                .into(iv);
    }

    /**
     * 加载图片
     *


     */
    public static void loadImage(String url, @DrawableRes int defaultImg, @DrawableRes int errImg, ImageView iv) {
        loadImage( url, errImg, defaultImg, iv, Priority.NORMAL, DiskCacheStrategy.RESULT);
    }

    /**
     * 加载设置优先级的图片
     *



     *        优先级：Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL(默认)，Priority.LOW
     * 缓存策略：DiskCacheStrategy.SOURCE：缓存原始数据，DiskCacheStrategy.RESULT：缓存变换(如缩放、裁剪等)后的资源数据(默认)，DiskCacheStrategy.NONE：什么都不缓存，DiskCacheStrategy.ALL：缓存SOURC和RESULT
     */
    public static void loadImage(String url, @DrawableRes int errImg, @DrawableRes int defaultImg, ImageView iv, Priority priority, DiskCacheStrategy diskCacheStrategy) {
        Glide.with(iv.getContext()).load(url).diskCacheStrategy(diskCacheStrategy).priority(priority).placeholder(defaultImg).error(errImg).into(iv);
    }

    /**
     * 加载gif图片(缓存原始数据)
     *
     *
     *      diskCacheStrategy设置缓存策略:
     */
    public static void loadGifImage(String url, ImageView iv) {
        Glide.with(iv.getContext()).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(default_img).into(iv);
    }

    /**
     * 加载圆形图片
     *
     *
     *
     */
    public static void loadCircleImage(String url, ImageView iv) {
//        Glide.with(iv.getContext())
//                .load(url)
//                .placeholder(default_circle_img)
//                .error(default_circle_err_img)
//                .transform(new GlideCircleTransform(iv.getContext())).into(iv);
        Glide
                .with(iv.getContext())
                .load(url)
                .placeholder(default_circle_img)
                .error(default_circle_err_img)
                .bitmapTransform(new CropCircleTransformation(iv.getContext()))
                .into(iv);
    }

    /**
     * 加载圆形资源图片
     *
     *
     *
     */
    public static void loadCircleImage(@DrawableRes int resourceId, ImageView iv) {
        Glide.with(iv.getContext())
                .load(resourceId)
                .dontAnimate()
                .placeholder(default_circle_img)
                .error(default_circle_err_img)
                .transform(new GlideCircleTransform(iv.getContext())).into(iv);
//        Glide.with(context).load(resourceId).bitmapTransform(new CropCircleTransformation(context)).into(iv);
    }

    /**
     * 加载圆角图片
     *

     *
     *
     *
     */
    public static void loadRoundCornerImage(String url, ImageView iv, int radius) {
        Glide.with(iv.getContext()).load(url).placeholder(default_img).error(default_err_img).transform(new GlideRoundTramsform(iv.getContext(), radius)).into(iv);
    }

    /**
     * 加载圆角资源图片
     *
     *
     *
     *
     */
    @Deprecated
    public static void loadRoundCornerImageDp(@DrawableRes int resourceId, ImageView iv, int radius) {
//        Glide.with(context).load(resourceId).dontAnimate().placeholder(default_img).error(default_err_img).transform(new GlideRoundTramsform(context, radius)).into(iv);
//        Glide.with(context).load(resourceId).placeholder(default_img).error(default_err_img).bitmapTransform(new RoundedCornersTransformation(context,radius,0, RoundedCornersTransformation.CornerType.ALL)).into(iv);
        Glide.with(iv.getContext()).load(resourceId).bitmapTransform(new RoundedCornersTransformation(iv.getContext(), radius, 0, RoundedCornersTransformation.CornerType.ALL)).into(iv);
    }

    /**
     * 加载圆角资源图片
     *
     * @param resourceId
     * @param iv
     * @param radiusPixel
     */
    public static void loadRoundCornerImage(@DrawableRes int resourceId, ImageView iv, int radiusPixel) {
        Glide.with(iv.getContext()).load(resourceId).bitmapTransform(new RoundedCornersTransformation(iv.getContext(), radiusPixel, 0, RoundedCornersTransformation.CornerType.ALL)).into(iv);
    }

    /**
     * 加载File类型图片
     *

     */
    public static void loadImage(File file, ImageView iv) {
        Glide.with(iv.getContext()).load(file).into(iv);
    }

    /**
     * 加载资源图片
     *

     */
    public static void loadImage(@DrawableRes int resourceId, ImageView iv) {
        Glide.with(iv.getContext()).load(resourceId).into(iv);
    }

    /**
     * 清除内存缓存
     *

     */
    public static void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * 清除磁盘缓存
     *

     */
    public static void clearDiskCache(Context applicationContext) {
        // 必须在后台线程中调用，建议同时clearMemory()
        Glide.get(applicationContext).clearDiskCache();
        clearMemory(applicationContext);
    }

    /**
     * 获取圆形Bitmap
     */
    private static class GlideCircleTransform extends BitmapTransformation {

        public GlideCircleTransform(Context context) {
            super(context);
        }

        public GlideCircleTransform(BitmapPool bitmapPool) {
            super(bitmapPool);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            //return null;
            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) {
                return null;
            }
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
            // 如果BitmapPool中找不到符合该条件的Bitmap，get()方法会返回null，就需要我们自己创建Bitmap
            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                // 如果想让Bitmap支持透明度，就需要使用ARGB_8888
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }
            //创建最终Bitmap的Canvas
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            // 将原始Bitmap处理后画到最终Bitmap中
            canvas.drawCircle(r, r, r, paint);
            // return我们新的Bitmap就行,Glide会自动帮我们回收原始Bitmap。
            return result;
        }

        @Override
        public String getId() {
            //return null;
            return getClass().getName();
        }
    }

    /**
     * 获取圆角的Bitmap
     */
    private static class GlideRoundTramsform extends BitmapTransformation {
        private static float radius = 0f;

        public GlideRoundTramsform(Context context) {
            super(context);
        }

        public GlideRoundTramsform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        public GlideRoundTramsform(BitmapPool bitmapPool) {
            super(bitmapPool);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            //return null;
            return roundCrop(pool, toTransform);
        }

        private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) {
                return null;
            }
            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            //return null;
            return getClass().getName() + Math.round(radius);
        }
    }


}
