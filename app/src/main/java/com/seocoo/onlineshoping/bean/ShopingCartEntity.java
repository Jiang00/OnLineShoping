package com.seocoo.onlineshoping.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * desc   :
 * author : Jiang
 * date   : 2019/1/14
 */


public class ShopingCartEntity {
    List<CommodityEntity> commodityEntities;
    String name;
    boolean isCheck;

    public ShopingCartEntity() {
       commodityEntities = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            ShopingCartEntity.CommodityEntity commodityEntity = new ShopingCartEntity.CommodityEntity();
            commodityEntity.setName(j + "aa");
            commodityEntities.add(commodityEntity);
        }
    }

    public List<CommodityEntity> getCommodityEntities() {
        return commodityEntities;
    }

    public void setCommodityEntities(List<CommodityEntity> commodityEntities) {
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

    public class CommodityEntity {
        String name;

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

        boolean isCheck;
    }
}
