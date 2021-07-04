package com.egen.thchome.service;

import com.egen.thchome.entity.CustomerOrder;
import com.egen.thchome.entity.Store;

import java.sql.Timestamp;
import java.util.List;

public interface StoreService {

    Boolean createStore(Store store);
    Boolean updateStore(Store store);
    Boolean deleteStore(String id);
    Store getStoreById(String id);
    public List<Store> getAllStores();
    public List<CustomerOrder> getStoreOrders(String storeId);


}
