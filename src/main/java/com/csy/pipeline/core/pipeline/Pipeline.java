package com.csy.pipeline.core.pipeline;

public interface Pipeline<T, S> {

    void addPipe(Pipe pipe);

    InvocationChain<T, S> newInvocation(T t, S s);

}
