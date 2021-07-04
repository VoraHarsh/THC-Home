package com.egen.thchome.service.impl;

import com.egen.thchome.entity.Interceptor;
import com.egen.thchome.repository.InterceptorRepository;
import com.egen.thchome.service.InterceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
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

}
