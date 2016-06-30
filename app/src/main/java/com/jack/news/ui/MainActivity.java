package com.jack.news.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jack.news.R;
import com.jack.news.adapter.NewsPagerAdapter;
import com.jack.news.base.BaseActivity;
import com.jack.news.bean.NewsChannelRepo;
import com.jack.news.presenter.impl.MainPresenter;
import com.jack.news.ui.interfaces.IMainView;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IMainView{

//    @Bind(R.id.toolbar)
//    Toolbar mToolbar;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;

    private NewsPagerAdapter mNewsPagerAdapter;
    private MainPresenter mainPresenter;

    private Boolean isExit = false;

    private List<NewsChannelRepo.ChannelEntity> channelEntityList = new ArrayList<NewsChannelRepo.ChannelEntity>();
    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
//        setSupportActionBar(mToolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initTab();

        mainPresenter.queryChannel(this);
    }

    public void initTab(){
        mNewsPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(),channelEntityList);
        mViewpager.setAdapter(mNewsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
    }

    @Override
    public void initPresenter() {
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public void channelResult(List<NewsChannelRepo.ChannelEntity> channelEntityList) {
        if (channelEntityList != null) {
            this.channelEntityList.addAll(channelEntityList);
            mNewsPagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onKeyDownExit();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 2秒退出程序
    public void onKeyDownExit() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            showToast("再按一次退出程序");
//            Snackbar.make(getWindow().getDecorView(), "再按一次退出程序", Snackbar.LENGTH_SHORT)
//                    .setAction("Action", null).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
