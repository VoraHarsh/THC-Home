package com.egen.thchome.controllers;

import com.egen.thchome.entity.Interceptor;
import com.egen.thchome.response.Response;
import com.egen.thchome.response.ResponseMetadata;
import com.egen.thchome.response.StatusMessage;
import com.egen.thchome.service.InterceptorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
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
    @ApiOperation(value  = "Returns the list of Api Execution Time given the name of method")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<List<Interceptor>> getApiByName(@PathVariable("methodName") String methodName){
        return Response.<List<Interceptor>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(interceptorService.getApiExecutionTime(methodName))
                .build();
    }

    @GetMapping("/{startTime}/{endTime}")
    @ApiOperation(value  = "Returns the list of Api Execution Time within Time Interval")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<List<Interceptor>> getAllApiWithinTimeInterval(@PathVariable("startTime") Timestamp startTime,@PathVariable("endTime") Timestamp endTime ){
        return Response.<List<Interceptor>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(interceptorService.getAllApiWithInInterval(startTime,endTime))
                .build();
    }

}
