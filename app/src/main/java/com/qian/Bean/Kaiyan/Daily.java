package com.qian.Bean.Kaiyan;

import java.util.List;

/**
 * Created by SHCai on 2017/8/22.
 */

public class Daily {


    /**
     * issueList : []
     * nextPageUrl : http://baobab.kaiyanapp.com/api/v2/feed?date=1503190800000&num=2
     * nextPublishTime : 1503450000000
     * newestIssueType : morning
     * dialog : null
     */

    private String nextPageUrl;
    private long nextPublishTime;
    private List<IssuList> issueList;



    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public void setNextPublishTime(long nextPublishTime) {
        this.nextPublishTime = nextPublishTime;
    }


    public List<IssuList> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<IssuList> issueList) {
        this.issueList = issueList;
    }
}
