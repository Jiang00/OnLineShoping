package com.seocoo.onlineshoping.bean;

import com.seocoo.onlineshoping.bean.api.CommodityBean;

import java.util.ArrayList;
import java.util.List;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/14
 */


public class ShopingCartEntity {
    List<CommodityBean> commodityEntities;
    String name;
    boolean isCheck;

    public ShopingCartEntity() {
        commodityEntities = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            CommodityBean commodityEntity = new CommodityBean();
            commodityEntity.setName(j + "aa");
            commodityEntities.add(commodityEntity);
        }
    }

    public List<CommodityBean> getCommodityEntities() {
        return commodityEntities;
    }

    public void setCommodityEntities(List<CommodityBean> commodityEntities) {
        this.commodityEntities = commodityEntities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

}
