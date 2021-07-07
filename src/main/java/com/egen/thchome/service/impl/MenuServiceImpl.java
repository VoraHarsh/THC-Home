package com.egen.thchome.service.impl;

import com.egen.thchome.entity.Menu;
import com.egen.thchome.entity.Store;
import com.egen.thchome.enums.MenuStatus;
import com.egen.thchome.exception.MenuServiceException;
import com.egen.thchome.exception.StoreServiceException;
import com.egen.thchome.repository.MenuRepository;
import com.egen.thchome.repository.StoreRepository;
import com.egen.thchome.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

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
    public Boolean createMenu(String storeId, Menu menu) {
        try{
            menu.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            Store store = storeRepository.findByStoreId(storeId);

            if(store == null){
                throw new StoreServiceException("Store does not exist");
            }

            Set<Menu> existingMenus = store.getMenu();
            existingMenus.add(menu);
            store.setMenu(existingMenus);
            storeRepository.save(store);
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
            Menu existingMenu = menuRepository.findByMenuId(menu.getMenuId());
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
            Menu existingMenu = menuRepository.findByMenuId(id);
            if(existingMenu == null){
                throw new MenuServiceException("Menu not found");
            }
            else{
                existingMenu.setMenuStatus(MenuStatus.DEACTIVATED);
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
            Menu menu = menuRepository.findByMenuId(id);
            return menu;
        }
        catch (Exception e){
            log.error("Error occurred in getting the Menu: " + e.getMessage());
            throw new MenuServiceException(e.getMessage());
        }
    }

    @Override
    public List<Menu> getAllMenus(int from, int to) {
        try {
            List<Menu> menus = menuRepository.findAll(PageRequest.of(from, to)).toList();
            return menus;
        }
        catch (Exception e){
            log.error("Error occurred in getting all the Menus: " + e.getMessage());
            throw new MenuServiceException(e.getMessage());
        }
    }
}
