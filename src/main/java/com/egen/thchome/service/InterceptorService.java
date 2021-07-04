package com.egen.thchome.service;

import com.egen.thchome.entity.Interceptor;

import java.util.List;

public interface InterceptorService {

    Interceptor addApiExecutionTime(Interceptor interceptor);

    List<Interceptor> getApiExecutionTime(String methodName);
}
