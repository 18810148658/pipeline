package com.csy.pipeline.core.engine;

import com.csy.pipeline.common.Result;

public interface EngineExecutor<T, S, R> {

    EngineName[] name();

    Result<R> execute(T t, S s, R r);

}
