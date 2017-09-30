package com.qian.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qian.R;
import com.qian.bean.MoodLog;

import java.util.List;

/**
 * Created by master on 2017/9/30.
 */

public class MoodLogPicAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    Context context;
    public MoodLogPicAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(context)
                .load(item)
                .into((ImageView) helper.getView(R.id.iv_mood_pic));
    }
}
