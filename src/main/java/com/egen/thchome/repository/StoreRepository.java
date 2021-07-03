package com.egen.thchome.repository;

import com.egen.thchome.entity.CustomerOrder;
import com.egen.thchome.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, String> {

    Store findStoreByStoreId(String id);

    @Query("SELECT store.orders FROM Store store WHERE store.storeId=:storeId")
    List<CustomerOrder> findStoreOrders(String storeId);
}
