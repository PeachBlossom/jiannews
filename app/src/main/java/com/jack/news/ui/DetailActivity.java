package com.jack.news.ui;

import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jack.news.R;
import com.jack.news.base.BaseActivity;
import com.jack.news.bean.NewsRepo;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class DetailActivity extends BaseActivity {
    @Bind(R.id.iv_image)
    SimpleDraweeView ivImage;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_source)
    TextView tvSource;
    @Bind(R.id.tv_address)
    TextView tvAddress;

    private NewsRepo.PagebeanEntity.ContentlistEntity mContentEntity;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_detail;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        //添加返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mContentEntity = (NewsRepo.PagebeanEntity.ContentlistEntity) getIntent().getSerializableExtra("data");
        if (mContentEntity != null) {
            collapsingToolbar.setTitle("   ");
            collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.white));
            collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

            tvTitle.setText(mContentEntity.getTitle());
            tvTime.setText(mContentEntity.getPubDate());
            tvSource.setText(mContentEntity.getSource());
            if (mContentEntity.getHtml() != null) {
                tvContent.setText(Html.fromHtml(mContentEntity.getHtml()));
            }
            ivImage.setImageResource(R.mipmap.default_news_img);
            if (mContentEntity.getImageurls() != null && !mContentEntity.getImageurls().isEmpty()) {
                Uri uri = Uri.parse(mContentEntity.getImageurls().get(0).getUrl());
                ivImage.setImageURI(uri);
            }
        }
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
