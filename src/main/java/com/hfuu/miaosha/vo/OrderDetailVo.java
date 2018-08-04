package com.hfuu.miaosha.vo;


import com.hfuu.miaosha.domain.OrderInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailVo {
	private GoodsVo goods;
	private OrderInfo order;

}
