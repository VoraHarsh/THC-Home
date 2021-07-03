package com.egen.thchome.controllers;

import com.egen.thchome.entity.Store;
import com.egen.thchome.response.Response;
import com.egen.thchome.response.ResponseMetadata;
import com.egen.thchome.response.StatusMessage;
import com.egen.thchome.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stores")
public class StoreController {

    StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping(produces = "application/json")
    public Response<List<Store>> getAllStores(){
        return Response.<List<Store>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(storeService.getAllStores())
                .build();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Response<Store> getStoreById(@PathVariable("id") String id){
        return Response.<Store>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(storeService.getStoreById(id))
                .build();
    }

    @PostMapping( value = "/createStore" , consumes = "application/json", produces = "application/json")
    public Response<String> createStore(@RequestBody Store store){
        return storeService.createStore(store) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Store Created")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Store Not Created")
                        .build();
    }




}
