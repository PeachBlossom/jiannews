package com.jack.news.presenter.impl;

import android.util.Log;

import com.jack.news.base.BasePresenter;
import com.jack.news.base.IBaseView;
import com.jack.news.bean.NewsChannelRepo;
import com.jack.news.bean.NewsRepo;
import com.jack.news.bean.Repo;
import com.jack.news.fragment.interfaces.INewsView;
import com.jack.news.net.NewsApiFactory;
import com.jack.news.presenter.interfaces.INewsPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class NewsPresenter extends BasePresenter implements INewsPresenter{

    public NewsPresenter(IBaseView mIBaseView) {
        super(mIBaseView);
    }


    @Override
    public void queryNews(final INewsView newsView, String channelId, String channelName, String title, String page) {
        Call<Repo<NewsRepo>> repoCall = NewsApiFactory.getNewsApiSingleton().getNews(channelId,channelName,title,page,"1","1");
        repoCall.enqueue(new Callback<Repo<NewsRepo>>() {
            @Override
            public void onResponse(Call<Repo<NewsRepo>> call, Response<Repo<NewsRepo>> response) {
                newsView.newsResult(response.body().getShowapi_res_body().getPagebean().getContentlist());
                if (response.body().getShowapi_res_body().getPagebean().getContentlist().size() == 0){
                    mIBaseView.showToast("休息会，还没更新哦");
                }
            }

            @Override
            public void onFailure(Call<Repo<NewsRepo>> call, Throwable t) {
                if (t != null && t.getMessage() != null) {
                    Log.e("queryNews", t.getLocalizedMessage());
                    mIBaseView.showToast(t.getMessage());
                }
            }
        });
    }
}
