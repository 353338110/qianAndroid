package com.qian.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qian.R;
import com.qian.bean.ChoosePicBean;
import com.qian.utils.BitmapUtils;

import java.util.List;

/**
 * Created by master on 2017/9/19.
 */

public class ChoosePicAdapter extends BaseMultiItemQuickAdapter<ChoosePicBean,BaseViewHolder>{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public static final int TYPEADD = 0;
    public static final int TYPENORMAL = 1;
    public ChoosePicAdapter(List<ChoosePicBean> data) {
        super(data);
        addItemType(TYPEADD, R.layout.item_choose_pic);
        addItemType(TYPENORMAL, R.layout.item_choose_pic);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChoosePicBean item) {
        if (item.getItemType()==TYPEADD){
            helper.setImageResource(R.id.iv_choose,R.mipmap.load_up);
        }else {
            helper.setImageBitmap(R.id.iv_choose, BitmapUtils.getLocalBitmap(item.getCompressPath()));
        }

    }

}
