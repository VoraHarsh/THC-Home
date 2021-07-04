package com.egen.thchome.service.impl;

import com.egen.thchome.entity.Menu;
import com.egen.thchome.exception.MenuServiceException;
import com.egen.thchome.repository.MenuRepository;
import com.egen.thchome.repository.StoreRepository;
import com.egen.thchome.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
@Slf4j
public class MenuServiceImpl implements MenuService {

    MenuRepository menuRepository;
    StoreRepository storeRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository, StoreRepository storeRepository){
        this.menuRepository = menuRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public Boolean createMenu(Menu menu) {
        try{
            menu.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            menuRepository.save(menu);
            return true;
        }
        catch(Exception e){
            log.error("Error occurred in creating Menu: " + e.getMessage());
            throw new MenuServiceException(e.getMessage());
        }
    }

    @Override
    public Boolean updateMenu(Menu menu) {
        try{
            Menu existingMenu = menuRepository.findMenuByMenuId(menu.getMenuId());
            if(existingMenu == null){
                throw new MenuServiceException("Menu does not exist");
            }
            else{
                menu.setModifiedDate(new Timestamp(System.currentTimeMillis()));
                menuRepository.save(menu);
                return true;
            }
        }
        catch(Exception e){
            log.error("Error occurred in updating the Menu: " + e.getMessage());
            throw new MenuServiceException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteMenu(String id) {
        try{
            Menu existingMenu = menuRepository.findMenuByMenuId(id);
            if(existingMenu == null){
                throw new MenuServiceException("Menu not found");
            }
            else{
//                List<Store> storeList = storeRepository.findByMenu(existingMenu);
//                for(Store store : storeList){
//                    store.setMenu(null);
//                }
//                storeRepository.save(storeList);
                menuRepository.save(existingMenu);
                return true;
            }
        }
        catch(Exception e){
            log.error("Error occurred in deleting the Menu: " + e.getMessage());
            throw new MenuServiceException(e.getMessage());
        }
    }

    @Override
    public Menu getMenuById(String id) {
        try {
            Menu menu = menuRepository.findMenuByMenuId(id);
            return menu;
        }
        catch (Exception e){
            log.error("Error occurred in getting the Menu: " + e.getMessage());
            throw new MenuServiceException(e.getMessage());
        }
    }

    @Override
    public List<Menu> getAllMenus() {
        try {
            List<Menu> menus = menuRepository.findAll();
            return menus;
        }
        catch (Exception e){
            log.error("Error occurred in getting all the Menus: " + e.getMessage());
            throw new MenuServiceException(e.getMessage());
        }
    }
}
