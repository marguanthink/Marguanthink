package net.juyun.www.jvyunlib.baserx;

import android.content.Context;

import net.juyun.www.jvyunlib.basebean.BaseRespose;
import net.juyun.www.jvyunlib.widget.LoadingDialog;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/8/18/018.
 * BaseRespose
 */

public abstract class SimpleSubscriber<T> extends Subscriber<BaseRespose<T>> {
    private Context context;
boolean isShow;
    public SimpleSubscriber(Context context) {
        this.context = context;
    }
    public SimpleSubscriber(Context context, boolean b) {
        this.context = context;
        isShow=b;
    }

    @Override
    public void onCompleted() {
        LoadingDialog.dismissLoadingDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isShow)
        LoadingDialog.showLoadingDialog(context);
    }

    @Override
    public void onError(Throwable e) {
        onError(e.getMessage());
        LoadingDialog.dismissLoadingDialog();
    }

    @Override
    public void onNext(BaseRespose<T> baseRespose) {
        if (baseRespose.success()) {
            onSuccess(baseRespose.data);
        } else {

            onError(baseRespose.info());
        }
        if (isShow)
        LoadingDialog.dismissLoadingDialog();
    }

    public abstract void onError(String error);

    public abstract void onSuccess(T data);

}
