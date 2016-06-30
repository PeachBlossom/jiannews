package com.jack.news.net;

import com.jack.news.bean.NewsChannelRepo;
import com.jack.news.bean.NewsRepo;
import com.jack.news.bean.Repo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public interface NewsApi {
    /**
     * 查询新闻频道
     * @return
     */
    @GET("/showapi_open_bus/channel_news/channel_news")
    Call<Repo<NewsChannelRepo>> getNewsChannel();

    /**
     * 查询新闻频道
     * @return
     */
    @GET("/showapi_open_bus/channel_news/channel_news")
    Call<String> getNewsStrChannel();

    /**
     *查询新闻
     * @param channelId 新闻频道id，必须精确匹配 //默认值 5572a109b3cdc86cf39001db
     * @param channelName 新闻频道名称，可模糊匹配 //默认值 国内最新
     * @param title 新闻标题，模糊匹配 //默认值 上市
     * @param page 页数，默认1。每页最多20条记录。 //默认值 1
     * @param needContent 是否需要返回正文，1为需要，其他为不需要 //默认值 0
     * @param needHtml 是否需要返回正文的html格式，1为需要，其他为不需要 //默认值 0
     * @return
     */
    @GET("/showapi_open_bus/channel_news/search_news")
    Call<Repo<NewsRepo>> getNews(@Query("channelId") String channelId
            , @Query("channelName") String channelName
            , @Query("title") String title
            , @Query("page") String page
            , @Query("needContent") String needContent
            , @Query("needHtml") String needHtml);

}
