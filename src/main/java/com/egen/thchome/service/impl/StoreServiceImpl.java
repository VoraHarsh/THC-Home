package com.egen.thchome.service.impl;

import com.egen.thchome.entity.CustomerOrder;
import com.egen.thchome.entity.Store;
import com.egen.thchome.exception.StoreServiceException;
import com.egen.thchome.repository.StoreRepository;
import com.egen.thchome.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }

    @Override
    public Boolean createStore(Store store) {
        try{
            storeRepository.save(store);
            return true;
        }
        catch(Exception e){
            throw new StoreServiceException("Store could not be created", e);
        }
    }

    @Override
    public Store getStoreById(String id) {
        try {
            Store store = storeRepository.findStoreByStoreId(id);
            return store;
        }
        catch (Exception e){
            throw new StoreServiceException("Store could not be found", e);
        }
    }

    @Override
    public List<Store> getAllStores() {
        try {
            List<Store> stores = storeRepository.findAll();
            return stores;
        }
        catch (Exception e){
            throw new StoreServiceException("Stores could not be found", e);
        }
    }

    @Override
    public List<CustomerOrder> getStoreOrders() {
        return null;
    }
}
