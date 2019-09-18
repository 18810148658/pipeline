package com.csy.pipeline.biz;

import com.csy.pipeline.biz.bo.CreateOrderContext;
import com.csy.pipeline.biz.bo.CreateOrderRequest;
import com.csy.pipeline.biz.bo.CreateOrderResponse;
import com.csy.pipeline.biz.pipe.*;
import com.csy.pipeline.common.Result;
import com.csy.pipeline.common.exception.ParamException;
import com.csy.pipeline.core.engine.AbstractEngineExecutor;
import com.csy.pipeline.core.engine.EngineExecutor;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CreateOrderEngineExecutor
        extends AbstractEngineExecutor<CreateOrderRequest, CreateOrderContext, CreateOrderResponse>
        implements EngineExecutor<CreateOrderRequest, CreateOrderContext, CreateOrderResponse> {

    /**
     * 公共管道
     */

    @Autowired
    protected CartPipe cartPipe;

    @Autowired
    protected CartByGoodsInfoPipe cartByGoodsInfoPipe;

    @Autowired
    protected AddressPipe addressPipe;

    @Autowired
    protected DepotPipe depotPipe;

    @Autowired
    protected FreightPipe freightPipe;

    @Autowired
    protected InsertOrderPipe insertOrderPipe;

    @Override
    public void validCommonParameter(CreateOrderRequest request) {
        if (request.getUserId() == null) {
            throw new ParamException("userId不能为空");
        }

        if (request.getOrderType() == null) {
            throw new ParamException("orderType不能为空");
        }

        if (request.getAddressId() == null) {
            throw new ParamException("addressId不能为空");
        }
    }

    @Override
    public Result<CreateOrderResponse> execute(CreateOrderRequest request, CreateOrderContext context, CreateOrderResponse response) {

        doPipeline(request, context);

        response.setOrderId(context.getOrderId());

        return Result.success(response);

    }

}
