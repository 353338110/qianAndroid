package com.qian.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qian.R;
import com.qian.bean.MoodLog;

import java.util.List;

/**
 * Created by master on 2017/9/30.
 */
public class MoodLogAdapter extends BaseQuickAdapter<MoodLog,BaseViewHolder> {
    Context context;
    public MoodLogAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<MoodLog> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MoodLog item) {
        helper.setText(R.id.tv_mood_content,item.getContent());

    }
}
