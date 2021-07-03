package com.egen.thchome.service.impl;

import com.egen.thchome.entity.Menu;
import com.egen.thchome.service.MenuService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Override
    public Boolean createMenu(Menu menu) {
        return null;
    }

    @Override
    public Menu getMenuById(String id) {
        return null;
    }

    @Override
    public List<Menu> getAllMenus() {
        return null;
    }
}
