package com.seocoo.onlineshoping.bean;

import com.seocoo.onlineshoping.bean.api.CommodityBean;

import java.util.ArrayList;
import java.util.List;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/16
 */
public class NearStoreEntity {
    String name;
    List<CommodityBean> commodityEntities;

    public NearStoreEntity() {
        commodityEntities = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            CommodityBean commodityEntity = new CommodityBean();
            commodityEntity.setName(j + "aa");
            commodityEntities.add(commodityEntity);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CommodityBean> getCommodities() {
        return commodityEntities;
    }

    public void setCommodities(List<CommodityBean> commodities) {
        this.commodityEntities = commodities;
    }
}
