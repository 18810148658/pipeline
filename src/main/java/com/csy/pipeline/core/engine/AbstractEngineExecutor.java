package com.csy.pipeline.core.engine;


import com.csy.pipeline.core.pipeline.InvocationChain;
import com.csy.pipeline.core.pipeline.Pipeline;
import com.csy.pipeline.core.pipeline.rollback.RollBack;
import com.csy.pipeline.core.pipeline.success.Success;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 抽象引擎
 * @param <T> 入参
 * @param <S> 上下文信息
 * @param <R> 接口返回值
 */
@Slf4j
public abstract class AbstractEngineExecutor<T, S, R> implements InitializingBean {

    @Autowired
    protected Pipeline pipeline;

    protected abstract void validParameter(T t);

    protected abstract String setKey(T t, S s);

    protected void doRollback(InvocationChain<T, S> invocationChain) {
        List<RollBack<T, S>> rollBackList = invocationChain.getRollBackList();
        if (CollectionUtils.isNotEmpty(rollBackList)) {
            for (RollBack<T, S> rollBack : rollBackList) {
                rollBack.rollBack(invocationChain);
            }
        }
    }

    protected void doSuccess(InvocationChain<T, S> invocationChain) {
        List<Success<T, S>> successList = invocationChain.getSuccessList();
        if (CollectionUtils.isNotEmpty(successList)) {
            for (Success<T, S> success : successList) {
                try {
                    success.success(invocationChain);
                } catch (Exception e) {
                    log.error("");
                }
            }
        }
    }

    protected void doPipeline(T t, S s) {

        validParameter(t);

        InvocationChain<T, S> invocationChain = pipeline.newInvocation(t, s);

        invocationChain.setKey(setKey(t, s));

        try {

            invocationChain.invoke();

        } catch (Throwable e) {

            doRollback(invocationChain);

            throw e;
        }

        doSuccess(invocationChain);

    }

}
