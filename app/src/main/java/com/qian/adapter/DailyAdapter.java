package com.qian.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qian.bean.kaiyan.Daily;
import com.qian.bean.kaiyan.ItemList;
import com.qian.R;

import java.util.List;

/**
 * Created by master on 2017/8/31.
 */

public class DailyAdapter extends BaseQuickAdapter<ItemList, BaseViewHolder>{

    public DailyAdapter(int layout,List< ItemList> list) {
        super(layout,list);
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
