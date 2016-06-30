package com.jack.news.presenter.interfaces;

import com.jack.news.ui.interfaces.IMainView;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public interface IMainPresenter {

    /**
     * 获取新闻频道
     * @param mainView
     */
    void queryChannel(IMainView mainView);
}
