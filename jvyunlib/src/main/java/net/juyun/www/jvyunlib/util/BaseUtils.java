package net.juyun.www.jvyunlib.util;


public abstract class BaseUtils {

    protected static String TAG;

    {
        TAG = this.getClass().getSimpleName();
    }

    protected BaseUtils() {
        throw new UnsupportedOperationException("You can't instantiate " + TAG + "...");
    }

}
