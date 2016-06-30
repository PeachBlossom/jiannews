package com.jack.news.presenter.interfaces;

import com.jack.news.fragment.interfaces.INewsView;
import com.jack.news.ui.interfaces.IMainView;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public interface INewsPresenter {

    void queryNews(INewsView newsView,String channelId,String channelName,String title,String page);

}
