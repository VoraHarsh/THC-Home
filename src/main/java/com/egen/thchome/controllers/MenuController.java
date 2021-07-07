package com.egen.thchome.controllers;

import com.egen.thchome.entity.Menu;
import com.egen.thchome.response.Response;
import com.egen.thchome.response.ResponseMetadata;
import com.egen.thchome.response.StatusMessage;
import com.egen.thchome.service.MenuService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/menus")
public class MenuController {

    MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping(value = "/{from}/{to}", produces = "application/json")
    @ApiOperation(value  = "Returns a List of all Menus in Range")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<List<Menu>> getAllMenus(@PathVariable("from") int from, @PathVariable("to") int to){
        return Response.<List<Menu>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(menuService.getAllMenus(from, to))
                .build();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value  = "Returns a single Menu given the MenuId")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<Menu> getMenuById(@PathVariable("id") String id){
        return Response.<Menu>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(menuService.getMenuById(id))
                .build();
    }

    @PostMapping( value = "/{storeId}/createMenu", consumes = "application/json", produces = "application/json")
    @ApiOperation(value  = "Creates Menu")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<String> createMenu(@PathVariable("storeId") String storeId, @RequestBody Menu menu){
        return menuService.createMenu(storeId, menu) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Menu Created")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Menu Not Created")
                        .build();
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    @ApiOperation(value  = "Update Menu")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<String> updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Menu Updated")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Menu Not Updated")
                        .build();
    }

    @PostMapping(value = "/delete/{id}")
    @ApiOperation(value  = "Delete Menu")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    public Response<String> deleteMenu(@PathVariable("id") String id){
        return menuService.deleteMenu(id) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Menu Deleted")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Menu Could not be Deleted")
                        .build();
    }


}
