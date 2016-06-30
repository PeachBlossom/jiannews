package com.jack.news.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jack.news.bean.NewsChannelRepo;
import com.jack.news.fragment.NewsFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
public class NewsPagerAdapter extends FragmentPagerAdapter {
    private List<NewsChannelRepo.ChannelEntity> titleArr;
    private FragmentManager mFragmentManager;

    public NewsPagerAdapter(FragmentManager fm,List<NewsChannelRepo.ChannelEntity>  titleArr) {
        super(fm);
        mFragmentManager = fm;
        if (titleArr == null){
            throw new NullPointerException("titleArr can't be null");
        }
        this.titleArr = titleArr;
    }

    @Override
    public Fragment getItem(int position) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("channelId",titleArr.get(position).getChannelId());
        bundle.putString("name",titleArr.get(position).getName());
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public int getCount() {
        return titleArr.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleArr.get(position).getName();
    }

}
