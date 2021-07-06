package com.egen.thchome.service.impl;

import com.egen.thchome.entity.CustomerOrder;
import com.egen.thchome.entity.Store;
import com.egen.thchome.enums.StoreStatus;
import com.egen.thchome.exception.StoreServiceException;
import com.egen.thchome.repository.StoreRepository;
import com.egen.thchome.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

            Set<CustomerOrder> orderSet = store.getOrders();

            for(CustomerOrder order : orderSet){
                order.setOrderCreatedDate(new Timestamp(System.currentTimeMillis()));
            }
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
            Store existingStore = storeRepository.findByStoreId(store.getStoreId());
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
            Store existingStore = storeRepository.findByStoreId(id);
            if(existingStore == null){
                throw new StoreServiceException("Store not found");
            }
            else{
                existingStore.setStoreStatus(StoreStatus.DEACTIVATED);
                storeRepository.save(existingStore);
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
            Store store = storeRepository.findByStoreId(id);
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
            Store existingStore = storeRepository.findByStoreId(storeId);
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

    @Override
    public List<CustomerOrder> getStoreOrdersBasedOnTime(String storeId) {
        try{
            Store existingStore = storeRepository.findByStoreId(storeId);
            if(existingStore == null){
                throw new StoreServiceException("Store does not exist");
            }
            else{
                List<CustomerOrder> orderList= storeRepository.findStoreOrders(storeId);
                List<CustomerOrder> finalList = new ArrayList<>();

                Timestamp yesterdayTime = new Timestamp(System.currentTimeMillis() - (1000L * 60L * 60L * 24L));

                for(CustomerOrder order : orderList) {
                    if (order.getOrderCreatedDate().after(yesterdayTime)) {
                        finalList.add(order);
                    }
                }
                if(finalList.isEmpty()){
                    throw new StoreServiceException("There are no New Orders");
                }
                return finalList;
            }
        }
        catch (Exception e){
            log.error("Error occurred in getting all the Store Orders Based on Time: " + e.getMessage());
            throw new StoreServiceException(e.getMessage());
        }
    }


}
