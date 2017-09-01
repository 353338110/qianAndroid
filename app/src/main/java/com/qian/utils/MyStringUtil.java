package com.qian.utils;

import com.qian.bean.kaiyan.Daily;
import com.qian.bean.kaiyan.ItemList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by master on 2017/8/31.
 */

public class MyStringUtil {
    //返回video中的data
    public static List<ItemList> getItemList(Daily daily){
        List<ItemList> itemLists = new ArrayList<>();
        for (int i = 0; i < daily.getIssueList().size(); i++) {
            for (int j = 0; j < daily.getIssueList().get(i).getItemList().size(); j++) {
                itemLists.add(daily.getIssueList().get(i).getItemList().get(j));
            }
        }
        return itemLists;
    }
}
