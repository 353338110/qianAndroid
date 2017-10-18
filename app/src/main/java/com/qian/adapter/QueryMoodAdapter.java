package com.qian.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qian.R;
import com.qian.bean.MoodLog;
import com.qian.utils.MyStringUtil;

import java.util.List;

/**
 * Created by master on 2017/10/18.
 */

public class QueryMoodAdapter extends BaseQuickAdapter<MoodLog,BaseViewHolder> {


    Context context;
    public QueryMoodAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<MoodLog> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MoodLog item) {
        helper.setText(R.id.tv_mood_content,item.getContent());
        List<String> urlList = MyStringUtil.changeUrlToList(item.getUrl());
        RecyclerView recyclerView = helper.getView(R.id.rcv_mood_photo);
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        recyclerView.setAdapter(new MoodLogPicAdapter(context,R.layout.item_mood_pic,urlList));
    }
}
