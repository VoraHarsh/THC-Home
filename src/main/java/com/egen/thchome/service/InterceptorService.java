package com.egen.thchome.service;

import com.egen.thchome.entity.Interceptor;

import java.sql.Timestamp;
import java.util.List;

public interface InterceptorService {

    Interceptor addApiExecutionTime(Interceptor interceptor);

    List<Interceptor> getAllApiByMethodName(String methodName);

    List<Interceptor> getAllApiWithInInterval(Timestamp startTime, Timestamp endTime);
}
