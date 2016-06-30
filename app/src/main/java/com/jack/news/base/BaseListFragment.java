package com.jack.news.base;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jack.news.R;
import com.jack.news.util.CacheManager;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
public abstract class BaseListFragment<T extends Object>  extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        AdapterView.OnItemClickListener,AbsListView.OnScrollListener {

    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;

    protected BaseListAdapter<T> mAdapter;

    protected int mCurrentPage = 1;
    protected String channelId;
    protected String channelName;

    //是否是下拉刷新
    protected Boolean isRefresh = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        channelId = bundle.getString("channelId");
        channelName = bundle.getString("name");
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_pull_refresh_listview;
    }

    @Override
    public void initView() {
        swiperefreshlayout.setOnRefreshListener(this);
        swiperefreshlayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);

        listview.setOnItemClickListener(this);
        listview.setOnScrollListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listview.setNestedScrollingEnabled(true);
        }

        if (mAdapter != null) {
            listview.setAdapter(mAdapter);
        } else {
            mAdapter = getListAdapter();
            listview.setAdapter(mAdapter);
            new CacheTask(getContext()).execute();
        }
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        sendRequestData();
    }

    public void setSwipeRefreshLoadedState(ArrayList<T> data){
        //吧数据存入缓存
        new SaveCacheTask(getActivity(), data, channelId).execute();
        if (data == null) {
            data = new ArrayList<T>();
        }

        if (isRefresh) {
            data.addAll(mAdapter.getData());
            mAdapter.clear();
        }

        int adapterState = BaseListAdapter.STATE_EMPTY_ITEM;
        if ((mAdapter.getCount() + data.size()) == 0) {
            adapterState = BaseListAdapter.STATE_EMPTY_ITEM;
        }  else {
            adapterState = BaseListAdapter.STATE_LOAD_MORE;
        }
        mAdapter.setState(adapterState);

        mAdapter.addData(data);
        mCurrentPage ++ ;
        if (swiperefreshlayout != null) {
            seSwiperefreshlayouttRefreshing(false);
        }
    }


    private class TextTask extends AsyncTask<String, Void, List<T>> {

        @Override
        protected void onCancelled(List<T> ts) {
            super.onCancelled(ts);
        }

        @Override
        protected void onPostExecute(List<T> ts) {
            super.onPostExecute(ts);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected List<T> doInBackground(String... params) {
            return null;
        }


    }
    //取出缓存线程
    private class CacheTask extends AsyncTask<String, Void, List<T>> {
        private final WeakReference<Context> mContext;

        private CacheTask(Context context) {
            mContext = new WeakReference<Context>(context);
        }

        @Override
        protected List<T> doInBackground(String... params) {
            Serializable seri = CacheManager.readObject(getContext(),
                    channelId);
            if (seri == null) {
                return null;
            } else {
                return (List<T>) seri;
            }
        }

        @Override
        protected void onPostExecute(List<T> list) {
            super.onPostExecute(list);
            //缓存有数据拿缓存数据，没有数据请求网络
            if (list != null) {
                ArrayList<T> arrayList = new ArrayList<T>();
                arrayList.addAll(list);
                mAdapter.setData(arrayList);
            }else{
                swiperefreshlayout.post(new Runnable() {
                    @Override
                    public void run() {
                        swiperefreshlayout.setRefreshing(true);
                        onRefresh();
                    }
                });
            }

        }
    }

    //存入缓存线程
    private class SaveCacheTask extends AsyncTask<Void, Void, Void> {
        private final WeakReference<Context> mContext;
        private final Serializable seri;
        private final String key;

        private SaveCacheTask(Context context, Serializable seri, String key) {
            mContext = new WeakReference<Context>(context);
            this.seri = seri;
            this.key = key;
        }

        @Override
        protected Void doInBackground(Void... params) {
            CacheManager.saveObject(mContext.get(), seri, key);
            return null;
        }
    }

    private void seSwiperefreshlayouttRefreshing(boolean isRefresh){
        if (swiperefreshlayout != null){
            swiperefreshlayout.setRefreshing(isRefresh);
        }
    }

    public void setSwipeRefreshLoadedState(){
        seSwiperefreshlayouttRefreshing(false);
    }

    /**
     * 获取刷新数据
     */
    protected abstract void sendRequestData();

    /**
     * 获取adapter
     * @return
     */
    protected abstract BaseListAdapter<T> getListAdapter();

    /**
     * list点击事件
     * @return
     */
    protected abstract void onListItemClick(AdapterView<?> parent, View view, int position, long id,T data);

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        T data = mAdapter.getData().get(position);
        onListItemClick(parent,view,position,id,data);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mAdapter == null || mAdapter.getCount() == 0) {
            return;
        }
        // 判断是否滚动到底部
        boolean scrollEnd = false;
        try {
            if (view.getPositionForView(mAdapter.getFooterView()) == view
                    .getLastVisiblePosition()) {
                scrollEnd = true;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            scrollEnd = false;
        }

        if (scrollEnd) {
            if (mAdapter.getState() == BaseListAdapter.STATE_LOAD_MORE
                    || mAdapter.getState() == BaseListAdapter.STATE_NETWORK_ERROR) {
                isRefresh = false;
                sendRequestData();
                mAdapter.setFooterViewLoading();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
