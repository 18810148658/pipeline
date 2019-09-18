package com.csy.pipeline.core.pipeline.rollback;

import com.csy.pipeline.core.pipeline.InvocationChain;

/**
 * 失败回调，比如发下单失败消息
 */
public interface RollBack<T, S> {

    void rollBack(InvocationChain<T, S> invocationChain);

}
