package com.csy.pipeline.biz.service;

import com.csy.pipeline.biz.bo.CreateOrderRequest;
import com.csy.pipeline.biz.bo.CreateOrderResponse;
import com.csy.pipeline.common.Result;

public interface CreateOrderService {

    Result<CreateOrderResponse> createOrder(CreateOrderRequest request);

}
