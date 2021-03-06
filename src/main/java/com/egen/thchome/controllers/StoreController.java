package com.egen.thchome.controllers;

import com.egen.thchome.entity.CustomerOrder;
import com.egen.thchome.entity.Store;
import com.egen.thchome.response.Response;
import com.egen.thchome.response.ResponseMetadata;
import com.egen.thchome.response.StatusMessage;
import com.egen.thchome.service.StoreService;
import com.egen.thchome.service.kafka.producer.ProducerServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stores")
public class StoreController {

    private StoreService storeService;
    private ProducerServiceImpl producerService;

    @Autowired
    public StoreController(StoreService storeService, ProducerServiceImpl producerService){
        this.storeService = storeService;
        this.producerService = producerService;
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value  = "Returns a List of all Stores")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<List<Store>> getAllStores(){
        return Response.<List<Store>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(storeService.getAllStores())
                .build();
    }

    @GetMapping(value = "/{from}/{to}", produces = "application/json")
    @ApiOperation(value  = "Returns the list of Stores in Range")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<List<Store>> getAllStoresInRange(@PathVariable("from") int from, @PathVariable("to") int to){
        return Response.<List<Store>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(storeService.getAllStoresInRange(from, to))
                .build();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value  = "Returns a single Store given the StoreId")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<Store> getStoreById(@PathVariable("id") String id){
        return Response.<Store>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(storeService.getStoreById(id))
                .build();
    }

    @PostMapping( value = "/createStore" , consumes = "application/json", produces = "application/json")
    @ApiOperation(value  = "Creates Stores")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
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
                                .statusCode(400)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Store Not Created")
                        .build();
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    @ApiOperation(value  = "Update Stores")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<String> updateStore(@RequestBody Store store){
        return storeService.updateStore(store) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Store Updated")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(400)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Store Not Updated")
                        .build();
    }

    @PostMapping(value = "/delete/{id}")
    @ApiOperation(value  = "Delete Stores")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<String> deleteStore(@PathVariable("id") String id){
        return storeService.deleteStore(id) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Store Deleted")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(400)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Store Could not be Deleted")
                        .build();
    }

    @GetMapping(value = "/storeOrders/{storeId}", produces = "application/json")
    @ApiOperation(value  = "Returns List of Store Orders given the StoreId")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<List<CustomerOrder>>getStoreOrders(@PathVariable("storeId") String storeId){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(storeService.getStoreOrders(storeId))
                .build();
    }

    @GetMapping(value = "/storeOrders/{storeId}/{from}/{to}", produces = "application/json")
    @ApiOperation(value  = "Returns List of Store Orders in Range given the StoreId")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<List<CustomerOrder>>getStoreOrdersInRange(@PathVariable("storeId") String storeId, @PathVariable int from, @PathVariable int to){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(storeService.getAllStoresOrdersInRange(storeId, from, to))
                .build();
    }

    @GetMapping(value = "/storeOrders/newOrders/{storeId}", produces = "application/json")
    @ApiOperation(value  = "Returns List of New Store Orders given the StoreId")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<List<CustomerOrder>>getStoreOrdersBasedOnTime(@PathVariable("storeId") String storeId){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(storeService.getStoreOrdersBasedOnTime(storeId))
                .build();
    }

    @PostMapping(value = "/publishOrders/{storeId}")
    public String publishOrder(@PathVariable("storeId") String storeId){
        List<CustomerOrder> customerOrderList = storeService.getStoreOrdersBasedOnTime(storeId);
        producerService.sendBatchOrder(customerOrderList);
        return "Order Received";
    }




}
