package net.juyun.www.jvyunlib.base;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import net.juyun.www.jvyunlib.BuildConfig;
import net.juyun.www.jvyunlib.R;
import net.juyun.www.jvyunlib.baseapp.AppManager;
import net.juyun.www.jvyunlib.baserx.RxManager;
import net.juyun.www.jvyunlib.commonutils.TUtil;
import net.juyun.www.jvyunlib.commonutils.ToastUitl;
import net.juyun.www.jvyunlib.commonwidget.LoadingDialog;
import net.juyun.www.jvyunlib.commonwidget.StatusBarCompat;
import net.juyun.www.jvyunlib.daynightmodeutils.ChangeModeController;

import butterknife.ButterKnife;

/**
 * 基类
 */

/***************使用例子*********************/
//1.mvp模式
//public class SampleActivity extends BaseActivity<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, mModel);
//    }
//
//    @Override
//    public void initView() {
//    }
//}
//2.普通模式
//public class SampleActivity extends BaseActivity {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//    }
//
//    @Override
//    public void initView() {
//    }
//}
public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity {
    public P mPresenter;
    public M mModel;
    public Context mContext;
    public RxManager mRxManager;
    private boolean isConfigChange = false;
    //布局填充器
    protected LayoutInflater mInflater;
    //标题栏
    protected Toolbar mToolBar;
    // 将被替换的内容布局
    protected FrameLayout mFmContent;
    // 标题返回局
    protected ImageView mIvTitleBack;
    // 标题名
    protected TextView mTvTitleName;
    // 标题右边文字
    protected TextView mTvTitleRight;
    // 标题右图片
    protected ImageView mIvTitleRight;
    //标题左文字
    protected TextView mTvTitleLeft;
    // 标题左图片
    protected ImageView mIvTitleLeft;
    //标题中间区域
    protected FrameLayout mFmTitleCenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isConfigChange = false;
        mRxManager = new RxManager();
        doBeforeSetcontentView();
        if (TextUtils.isEmpty(getLayoutTitle())) {
            setContentView(getLayoutId());
        } else {
            mInflater = getLayoutInflater();
            setContentView(R.layout.activity_base);
            mToolBar = getViewById(R.id.tb_base);
            mFmContent = getViewById(R.id.fm_base_content);
            mIvTitleLeft = getViewById(R.id.iv_title_left);
            mTvTitleLeft = getViewById(R.id.tv_title_left);
            mTvTitleRight = getViewById(R.id.tv_title_right);
            mIvTitleRight = getViewById(R.id.iv_title_right);
            mIvTitleBack = getViewById(R.id.iv_title_back);
            mTvTitleName = getViewById(R.id.tv_title);
            mFmTitleCenter = getViewById(R.id.fm_title_center);

            setSupportActionBar(mToolBar);
            if (getLayoutId() > 0) {
                mInflater.inflate(getLayoutId(), mFmContent);
            }
            mTvTitleName.setText(getLayoutTitle());
            setOnClickListeners(mOnClickListener, mIvTitleBack);
        }

        ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        this.initPresenter();
        this.initView();
    }

    /**
     * 查找View,不用强制转型
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return 对应的View
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        //设置昼夜主题
        initTheme();
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
        SetStatusBarColor();

    }

    /**
     * 为多个View 添加点击事件
     *
     * @param listener
     * @param views
     */
    protected void setOnClickListeners(View.OnClickListener listener, View... views) {
        if (listener != null) {
            for (View view : views) {
                view.setOnClickListener(listener);
            }
        }
    }

    /*********************子类实现*****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //获取布局文件
    public abstract String getLayoutTitle();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化view
    public abstract void initView();


    /**
     * 设置主题
     */
    private void initTheme() {
        ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(this);
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(this, msg, true);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }

    /**
     * 带图片的toast
     *
     * @param text
     * @param res
     */
    public void showToastWithImg(String text, int res) {
        ToastUitl.showToastWithImg(text, res);
    }

    /**
     * 网络访问错误提醒
     */
    public void showNetErrorTip() {
        ToastUitl.showToastWithImg(getText(R.string.net_error).toString(), R.drawable.ic_wifi_off);
    }

    public void showNetErrorTip(String error) {
        ToastUitl.showToastWithImg(error, R.drawable.ic_wifi_off);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //debug版本不统计crash
        if (!BuildConfig.LOG_DEBUG) {
            //友盟统计
            MobclickAgent.onResume(this);
        }
    }

    /**
     * 通过类名启动Activity
     *
     * @param cls 要跳转的Activity
     */
    protected void openActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //debug版本不统计crash
        if (!BuildConfig.LOG_DEBUG) {
            //友盟统计
            MobclickAgent.onPause(this);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isConfigChange = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        if (mRxManager != null) {
            mRxManager.clear();
        }
        if (!isConfigChange) {
            AppManager.getAppManager().finishActivity(this);
        }


    }

    /**
     * 不需要为返回键设置监听，默认为finish当前activity
     */
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.iv_title_back) {
                onBackPressed();

            }

        }
    };
}
