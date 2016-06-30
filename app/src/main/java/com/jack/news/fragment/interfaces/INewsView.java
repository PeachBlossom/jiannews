package com.jack.news.fragment.interfaces;

import com.jack.news.bean.NewsRepo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public interface INewsView {

    void newsResult(List<NewsRepo.PagebeanEntity.ContentlistEntity> contentlistEntityList);
}
