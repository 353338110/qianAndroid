package com.qian.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by master on 2017/9/19.
 */

public class ChoosePicBean implements MultiItemEntity {

    private String path;
    private String compressPath;
    private int width;
    private int height;
    private int type;

    public ChoosePicBean() {
    }

    public ChoosePicBean(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCompressPath() {
        return compressPath;
    }

    public void setCompressPath(String compressPath) {
        this.compressPath = compressPath;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
