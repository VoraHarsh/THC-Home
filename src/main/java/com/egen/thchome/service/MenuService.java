package com.egen.thchome.service;

import com.egen.thchome.entity.Menu;

import java.util.List;

public interface MenuService {

    Boolean createMenu(String storeId, Menu menu);
    Boolean updateMenu(Menu menu);
    Boolean deleteMenu(String id);
    Menu getMenuById(String id);
    public List<Menu> getAllMenus();

}
