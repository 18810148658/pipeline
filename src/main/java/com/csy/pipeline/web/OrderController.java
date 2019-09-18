package com.csy.pipeline.web;

import com.csy.pipeline.biz.bo.CreateOrderRequest;
import com.csy.pipeline.biz.bo.CreateOrderResponse;
import com.csy.pipeline.biz.service.CreateOrderService;
import com.csy.pipeline.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CreateOrderService createOrderService;

    @PostMapping("/createOrder")
    public Result<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return createOrderService.createOrder(request);
    }

}
