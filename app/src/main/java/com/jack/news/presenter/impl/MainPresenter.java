package com.jack.news.presenter.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.jack.news.base.BasePresenter;
import com.jack.news.base.IBaseView;
import com.jack.news.bean.NewsChannelRepo;
import com.jack.news.bean.Repo;
import com.jack.news.net.NewsApiFactory;
import com.jack.news.presenter.interfaces.IMainPresenter;
import com.jack.news.ui.interfaces.IMainView;
import com.jack.news.util.CacheManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class MainPresenter extends BasePresenter implements IMainPresenter {
    private final static String CHANNELE_FILE_NAME = "newschannel";

    public MainPresenter(IBaseView mIBaseView) {
        super(mIBaseView);
    }

    @Override
    public void queryChannel(final IMainView mainView) {
        //先查看channel是否有缓存，有缓存用缓存
        NewsChannelRepo newsChannelRepo = (NewsChannelRepo) CacheManager.readObject(mIBaseView.getContext(),CHANNELE_FILE_NAME);
        if (newsChannelRepo != null && newsChannelRepo.getChannelList() != null){
            mainView.channelResult(newsChannelRepo.getChannelList());
        }else {
            mIBaseView.showProgress();
            Call<Repo<NewsChannelRepo>> repoCall = NewsApiFactory.getNewsApiSingleton().getNewsChannel();
            repoCall.enqueue(new Callback<Repo<NewsChannelRepo>>() {
                @Override
                public void onResponse(Call<Repo<NewsChannelRepo>> call, Response<Repo<NewsChannelRepo>> response) {
                    List<NewsChannelRepo.ChannelEntity> channelEntityList = response.body().getShowapi_res_body().getChannelList();
                    if (channelEntityList != null) {
                        //存入缓存
                        CacheManager.saveObject(mIBaseView.getContext(), response.body().getShowapi_res_body(), CHANNELE_FILE_NAME);
                    }
                    mainView.channelResult(channelEntityList);
                    Log.e("queryChannel", response.toString());
                    mIBaseView.hideProgress();
                }

                @Override
                public void onFailure(Call<Repo<NewsChannelRepo>> call, Throwable t) {
                    if (t != null && t.getMessage() != null) {
                        Log.e("queryChannel", t.getMessage());
                        mIBaseView.showToast(t.getMessage());
                    }
                    mIBaseView.hideProgress();
                }
            });
        }
    }
}
