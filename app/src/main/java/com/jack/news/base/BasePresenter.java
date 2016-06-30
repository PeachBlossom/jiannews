package com.jack.news.base;


/**
 * Created by Administrator on 2016/5/26 0026.
 */
public class BasePresenter {
    protected IBaseView mIBaseView;

    public BasePresenter(IBaseView mIBaseView){
        this.mIBaseView = mIBaseView;
    }

}
