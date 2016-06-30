package com.jack.news.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.jack.news.adapter.NewsAdapter;
import com.jack.news.base.BaseListAdapter;
import com.jack.news.base.BaseListFragment;
import com.jack.news.bean.NewsRepo;
import com.jack.news.fragment.interfaces.INewsView;
import com.jack.news.presenter.impl.NewsPresenter;
import com.jack.news.presenter.interfaces.INewsPresenter;
import com.jack.news.ui.DetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
public class NewsFragment extends BaseListFragment<NewsRepo.PagebeanEntity.ContentlistEntity> implements INewsView {
    private INewsPresenter mNewsPresenter;

    @Override
    public void initPresenter() {
        mNewsPresenter = new NewsPresenter(this);
    }

    @Override
    protected void sendRequestData() {
        mNewsPresenter.queryNews(this,channelId,channelName,"",mCurrentPage + "");
    }

    @Override
    protected BaseListAdapter<NewsRepo.PagebeanEntity.ContentlistEntity> getListAdapter() {
        return new NewsAdapter();
    }

    @Override
    protected void onListItemClick(AdapterView<?> parent, View view, int position, long id, NewsRepo.PagebeanEntity.ContentlistEntity data) {
        Intent intent = new Intent(baseActivity, DetailActivity.class);
        intent.putExtra("data",data);
//        intent.putExtra("",data);
        baseActivity.startActivity(intent);
    }

    @Override
    public void newsResult(List<NewsRepo.PagebeanEntity.ContentlistEntity> contentlistEntityList) {
        if (contentlistEntityList != null){
            ArrayList<NewsRepo.PagebeanEntity.ContentlistEntity> arrayList = new ArrayList<>();
            arrayList.addAll(contentlistEntityList);
            setSwipeRefreshLoadedState(arrayList);
        }
    }
    
}
