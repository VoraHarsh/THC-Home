package com.egen.thchome.service.impl;

import com.egen.thchome.entity.CustomerOrder;
import com.egen.thchome.entity.Store;
import com.egen.thchome.exception.StoreServiceException;
import com.egen.thchome.repository.StoreRepository;
import com.egen.thchome.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
@Slf4j
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
            log.error("Error occurred in creating the Store" + e.getMessage());
            throw new StoreServiceException(e.getMessage());
        }
    }

    @Override
    public Boolean updateStore(Store store) {
        try{
            Store existingStore = storeRepository.findStoreByStoreId(store.getStoreId());
            if(existingStore == null){
                throw new StoreServiceException("Store does not exist");
            }
            else{
                storeRepository.save(store);
                return true;
            }
        }
        catch(Exception e){
            log.error("Error occurred in updating the Store: " + e.getMessage());
            throw new StoreServiceException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteStore(String id) {
        try{
            Store existingStore = storeRepository.findStoreByStoreId(id);
            if(existingStore == null){
                throw new StoreServiceException("Store not found");
            }
            else{
                storeRepository.deleteById(id);
                return true;
            }
        }
        catch(Exception e){
            log.error("Error occurred in deleting the Store: " + e.getMessage());
            throw new StoreServiceException(e.getMessage());
        }
    }

    @Override
    public Store getStoreById(String id) {
        try {
            Store store = storeRepository.findStoreByStoreId(id);
            return store;
        }
        catch (Exception e){
            log.error("Error occurred in getting the Store" + e.getMessage());
            throw new StoreServiceException(e.getMessage());
        }
    }

    @Override
    public List<Store> getAllStores() {
        try {
            List<Store> stores = storeRepository.findAll();
            return stores;
        }
        catch (Exception e){
            log.error("Error occurred in getting all the Stores" + e.getMessage());
            throw new StoreServiceException(e.getMessage());
        }
    }

    @Override
    public List<CustomerOrder> getStoreOrders(String storeId) {
        try{
            Store existingStore = storeRepository.findStoreByStoreId(storeId);
            if(existingStore == null){
                throw new StoreServiceException("Store does not exist");
            }
            else{
                List<CustomerOrder> orderList= storeRepository.findStoreOrders(storeId);
                return orderList;
            }
        }
        catch (Exception e){
            log.error("Error occurred in getting all the Store Orders" + e.getMessage());
            throw new StoreServiceException(e.getMessage());
        }
    }

}
