package com.jack.news.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.jack.news.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/20 0020.
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView{
    private ProgressDialog mProgressDialog;

    /**
     * 初始化布局
     */
    public abstract int getLayoutRes();
    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化控制中心
     */
    public abstract void initPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        // 初始化View注入
        ButterKnife.bind(this);

        initPresenter();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void showProgress(boolean flag, String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(flag);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(message);
        }
        mProgressDialog.show();
    }

    @Override
    public void showProgress(String message) {
        showProgress(true, message);
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void showProgress(boolean flag) {
        showProgress(flag, "");
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog == null)
            return;

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showToast(int resId) {
        showToast(getString(resId));
    }

    @Override
    public void showToast(String msg) {
        if (!isFinishing()) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void close() {
        finish();
    }
}
