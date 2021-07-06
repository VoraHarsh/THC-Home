package com.egen.thchome.interceptor;

import com.egen.thchome.entity.Interceptor;
import com.egen.thchome.service.InterceptorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;


@Slf4j
@Component
public class StoreServiceInterceptor implements HandlerInterceptor {

    private Timestamp startTime;
    private Timestamp endTime;

    @Autowired
    InterceptorService interceptorService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("In preHandle method");
        System.out.println("In preHandle Method");
        startTime = new Timestamp(System.currentTimeMillis());
        String url = request.getRequestURI();
        System.out.println(url);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("In postHandle Method");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("In afterCompletion Method");
        String controllerName="";
        String actionName="";

        endTime = new Timestamp(System.currentTimeMillis());

        Interceptor interceptor = new Interceptor();
        interceptor.setStartTime(startTime);
        interceptor.setEndTime(endTime);
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            controllerName = handlerMethod.getBeanType().getSimpleName();
            actionName = handlerMethod.getMethod().getName();
        }

        System.out.println(controllerName);
        System.out.println(actionName);

        interceptor.setMethodName(actionName);

        interceptor.setExecutionTime(endTime.getTime() - startTime.getTime());

        interceptorService.addApiExecutionTime(interceptor);

    }


}
