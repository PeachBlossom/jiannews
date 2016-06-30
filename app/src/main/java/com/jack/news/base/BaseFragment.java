package com.jack.news.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public abstract class BaseFragment extends Fragment implements IBaseView {
    protected BaseActivity baseActivity;
    protected View mLayoutView;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseActivity = (BaseActivity) getActivity();
    }

    /**
     * 初始化布局
     */
    public abstract int getLayoutRes();

    /**
     * 初始化视图
     */
    public abstract void initView();

    public abstract void initPresenter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutView = inflater.inflate(getLayoutRes(), container, false);
        ButterKnife.bind(this, mLayoutView);
        initPresenter();
        initView();
        return mLayoutView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 获取当前Fragment状态
     *
     * @return true为正常 false为未加载或正在删除
     */
    private boolean getStatus() {
        return (isAdded() && !isRemoving());
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (baseActivity != null && getStatus()) {
            baseActivity.showProgress(flag,message);
        }
    }

    @Override
    public void showProgress(String message) {
        if (baseActivity != null && getStatus()) {
            baseActivity.showProgress(message);
        }
    }

    @Override
    public void showProgress() {
        if (baseActivity != null && getStatus()) {
            baseActivity.showProgress();
        }
    }

    @Override
    public void showProgress(boolean flag) {
        if (baseActivity != null && getStatus()) {
            baseActivity.showProgress(flag);
        }
    }

    @Override
    public void hideProgress() {
        if (baseActivity != null && getStatus()) {
            baseActivity.hideProgress();
        }
    }

    @Override
    public void showToast(int resId) {
        if (baseActivity != null && getStatus()) {
            baseActivity.showToast(resId);
        }
    }

    @Override
    public void showToast(String msg) {
        if (baseActivity != null && getStatus()) {
            baseActivity.showToast(msg);
        }
    }

    @Override
    public void close() {

    }
}
