package com.egen.thchome.service;

import com.egen.thchome.entity.CustomerOrder;
import com.egen.thchome.entity.Menu;
import com.egen.thchome.entity.Store;

import java.util.List;

public interface MenuService {

    Boolean createMenu(Menu menu);
    Menu getMenuById(String id);
    public List<Menu> getAllMenus();

}
