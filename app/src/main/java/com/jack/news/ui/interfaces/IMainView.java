package com.jack.news.ui.interfaces;

import com.jack.news.bean.NewsChannelRepo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
public interface IMainView {

    /**
     * 获取新闻频道
     */
    void channelResult(List<NewsChannelRepo.ChannelEntity> channelEntityList);
}
