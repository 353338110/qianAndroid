package com.qian.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qian.Bean.Kaiyan.ItemList;
import com.qian.R;

/**
 * Created by master on 2017/8/31.
 */

public class DailyAdapter extends BaseQuickAdapter<ItemList, BaseViewHolder>{

    public DailyAdapter() {
        super(R.layout.item_daily);
    }
    @Override
    protected void convert(BaseViewHolder helper, ItemList item) {
        if (null==item.data.author){
            helper.setVisible(R.id.tv_daily_author,false);
        }else {
            helper.setVisible(R.id.tv_daily_author,true)
            .setText(R.id.tv_daily_author,item.data.author.name);
        }
        helper.setText(R.id.tv_daily_title,item.data.title+"\n");
        Glide.with(mContext)
                .load(item.data.cover.detail)
                .into((ImageView) helper.getView(R.id.iv_daily));
    }
}
