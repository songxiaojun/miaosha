package com.hfuu.miaosha.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Goods {
    private Integer goodsId;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;
}
