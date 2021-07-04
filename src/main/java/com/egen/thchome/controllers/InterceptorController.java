package com.egen.thchome.controllers;

import com.egen.thchome.entity.Interceptor;
import com.egen.thchome.response.Response;
import com.egen.thchome.response.ResponseMetadata;
import com.egen.thchome.response.StatusMessage;
import com.egen.thchome.service.InterceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/interceptor")
public class InterceptorController {

    private InterceptorService interceptorService;

    @Autowired
    public InterceptorController(InterceptorService interceptorService){
        this.interceptorService = interceptorService;
    }

    @GetMapping("/{methodName}")
    public Response<List<Interceptor>> getApiExecutionTime(@PathVariable("methodName") String methodName){
        return Response.<List<Interceptor>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(interceptorService.getApiExecutionTime(methodName))
                .build();
    }

}
