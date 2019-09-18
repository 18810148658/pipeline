package com.csy.pipeline.biz.executor;

import com.csy.pipeline.biz.CreateOrderEngineExecutor;
import com.csy.pipeline.biz.pipe.bargain.BargainPipe;
import com.csy.pipeline.biz.pipe.bargain.BuildBargainOrderPipe;
import com.csy.pipeline.biz.bo.*;
import com.csy.pipeline.common.exception.ParamException;
import com.csy.pipeline.core.engine.EngineName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateBargainOrderEngineExecutor extends CreateOrderEngineExecutor {

    @Autowired
    private BuildBargainOrderPipe buildBargainOrderPipe;

    @Autowired
    private BargainPipe bargainPipe;

    @Override
    protected void validCustomParameter(CreateOrderRequest request) {
        if (request.getBargainInfoId() == null) {
            throw new ParamException("砍价信息不能为空");
        }
    }

    @Override
    protected String setKey(CreateOrderRequest createOrderRequest, CreateOrderContext createOrderContext) {
        return "createBargainOrder_" + createOrderRequest.getUserId();
    }

    @Override
    public void afterPropertiesSet() {
        pipeline.addPipe(bargainPipe);
        pipeline.addPipe(addressPipe);
        pipeline.addPipe(depotPipe);
        pipeline.addPipe(freightPipe);
        pipeline.addPipe(buildBargainOrderPipe);
        pipeline.addPipe(insertOrderPipe);
    }

    @Override
    public EngineName[] name() {
        return new EngineName[]{EngineName.CREATE_BARGAIN_ORDER};
    }

}
