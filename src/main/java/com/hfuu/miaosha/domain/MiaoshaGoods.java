package com.hfuu.miaosha.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class MiaoshaGoods {
    private Integer id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
