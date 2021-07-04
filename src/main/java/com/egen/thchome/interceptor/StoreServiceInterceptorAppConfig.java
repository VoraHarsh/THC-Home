package com.egen.thchome.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class StoreServiceInterceptorAppConfig extends WebMvcConfigurationSupport {

    @Autowired
    StoreServiceInterceptor storeServiceInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(storeServiceInterceptor);
    }
}
