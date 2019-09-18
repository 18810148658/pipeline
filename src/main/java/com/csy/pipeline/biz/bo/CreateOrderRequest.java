package com.csy.pipeline.biz.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {

    private Long cartId;

    private Long userId;

    private Long catId;

    private Long skuId;

    private Integer orderType;

}
