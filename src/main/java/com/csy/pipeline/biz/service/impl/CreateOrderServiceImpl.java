package com.csy.pipeline.biz.service.impl;

import com.csy.pipeline.biz.bo.CreateOrderContext;
import com.csy.pipeline.biz.bo.CreateOrderRequest;
import com.csy.pipeline.biz.bo.CreateOrderResponse;
import com.csy.pipeline.biz.service.CreateOrderService;
import com.csy.pipeline.common.Result;
import com.csy.pipeline.core.EngineResolver;
import com.csy.pipeline.core.aop.ServerCatch;
import com.csy.pipeline.core.engine.EngineExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {

    @Autowired
    private EngineResolver engineResolver;

    @ServerCatch
    @Override
    public Result<CreateOrderResponse> createOrder(CreateOrderRequest request) {

        EngineExecutor engineExecutor = engineResolver.getExecutor(request.getOrderType());
        if (engineExecutor == null) {
            throw new RuntimeException("未找到创建订单执行器 orderType = " + request.getOrderType());
        }

        return engineExecutor.execute(request, new CreateOrderContext(), new CreateOrderResponse());
    }

}
