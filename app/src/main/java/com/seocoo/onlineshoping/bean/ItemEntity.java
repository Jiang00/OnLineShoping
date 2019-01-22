package com.seocoo.onlineshoping.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * desc:    通用item对象 我的 首页等 简化布局 标签页
 *
 * @author Bian
 * create at 2018/12/18
 */
public class ItemEntity implements CustomTabEntity {
    private int backgroundId;
    private int iconId;
    private int titleId;
    private String desc;


    public ItemEntity() {
    }


    public ItemEntity(String desc) {
        this.desc = desc;
    }

    public ItemEntity(int iconId, int titleId) {
        this.iconId = iconId;
        this.titleId = titleId;
    }

    public ItemEntity(int iconId, int titleId, String desc) {
        this.iconId = iconId;
        this.titleId = titleId;
        this.desc = desc;
    }

    public ItemEntity(int backgroundId, int iconId, int titleId, String desc) {
        this.backgroundId = backgroundId;
        this.iconId = iconId;
        this.titleId = titleId;
        this.desc = desc;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    @Override
    public String getTabTitle() {
        return desc;
    }

    @Override
    public int getTabSelectedIcon() {
        return iconId;
    }

    @Override
    public int getTabUnselectedIcon() {
        return backgroundId;
    }
}
