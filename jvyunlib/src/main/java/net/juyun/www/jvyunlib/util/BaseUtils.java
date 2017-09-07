package net.juyun.www.jvyunlib.util;

/**
 * Created by DUANLU on 2017/3/9  16:38.
 *
 * @author DUANLU
 * @version 1.0.0
 * @class BaseUtils
 * @describe The base class for all utils.
 * 注：All tools must extends this class in the project.
 */
public abstract class BaseUtils {

    protected static String TAG;

    {
        TAG = this.getClass().getSimpleName();
    }

    protected BaseUtils() {
        throw new UnsupportedOperationException("You can't instantiate " + TAG + "...");
    }

}
