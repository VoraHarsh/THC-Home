package com.egen.thchome.service.impl;

import com.egen.thchome.entity.Interceptor;
import com.egen.thchome.exception.StoreServiceException;
import com.egen.thchome.repository.InterceptorRepository;
import com.egen.thchome.service.InterceptorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class InterceptorServiceImpl implements InterceptorService {

    private InterceptorRepository interceptorRepository;

    @Autowired
    public InterceptorServiceImpl(InterceptorRepository interceptorRepository){
        this.interceptorRepository = interceptorRepository;
    }

    public Interceptor addApiExecutionTime(Interceptor interceptor){
        return interceptorRepository.save(interceptor);
    }

    @Override
    public List<Interceptor> getApiExecutionTime(String methodName) {
        return interceptorRepository.findByMethodNameEndsWith(methodName);
    }

    @Override
    public List<Interceptor> getAllApiWithInInterval(Timestamp startTime, Timestamp endTime) {
        try{
            return interceptorRepository.findAllInterceptorsWithInInterval(startTime, endTime);
        }
        catch (Exception e){
            log.error("Error occurred in getting all the api's within Time Interval: " + e.getMessage());
            throw new StoreServiceException(e.getMessage());
        }
    }

}
