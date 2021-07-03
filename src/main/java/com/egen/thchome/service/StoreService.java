package com.egen.thchome.service;

import com.egen.thchome.entity.CustomerOrder;
import com.egen.thchome.entity.Store;

import java.util.List;

public interface StoreService {

    Boolean createStore(Store store);
    Store getStoreById(String id);
    public List<Store> getAllStores();
    public List<CustomerOrder> getStoreOrders();


}
