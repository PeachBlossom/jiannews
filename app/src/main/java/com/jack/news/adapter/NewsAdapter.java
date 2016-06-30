package com.jack.news.adapter;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jack.news.R;
import com.jack.news.base.BaseListAdapter;
import com.jack.news.bean.NewsRepo;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class NewsAdapter extends BaseListAdapter<NewsRepo.PagebeanEntity.ContentlistEntity> {

    @Override
    public View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.list_cell_news, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        NewsRepo.PagebeanEntity.ContentlistEntity contentlistEntity = mDatas.get(position);
        vh.title.setText(contentlistEntity.getTitle());
        vh.source.setText(contentlistEntity.getSource());
        vh.time.setText(contentlistEntity.getPubDate());
        if (contentlistEntity.getImageurls() != null && !contentlistEntity.getImageurls().isEmpty()){
            Uri uri = Uri.parse(contentlistEntity.getImageurls().get(0).getUrl());
            vh.iv_image.setImageURI(uri);
            vh.iv_image.setVisibility(View.VISIBLE);
        }else{
            vh.iv_image.setVisibility(View.GONE);
        }

        return convertView;
    }

    private void testThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class ViewHolder {
        @Bind(R.id.tv_title)
        TextView title;
//        @Bind(R.id.tv_description)
//        TextView description;
        @Bind(R.id.tv_source)
        TextView source;
        @Bind(R.id.tv_time)
        TextView time;
        @Bind(R.id.tv_comment_count)
        TextView comment_count;
        @Bind(R.id.iv_image)
        SimpleDraweeView iv_image;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
