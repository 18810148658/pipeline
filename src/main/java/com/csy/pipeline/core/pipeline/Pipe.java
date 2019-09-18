package com.csy.pipeline.core.pipeline;

public interface Pipe<T, S> {

    void invoke(InvocationChain<T, S> invocationChain);

}
