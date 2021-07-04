package com.egen.thchome.service.impl;

import com.egen.thchome.entity.*;
import com.egen.thchome.repository.StoreRepository;
import com.egen.thchome.service.StoreService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class StoreServiceImplTest {

    @MockBean
    private StoreRepository storeRepository;

    @Autowired
    private StoreService storeService;


    @Bean
    public StoreService getStoreService(){
        return new StoreServiceImpl(storeRepository);
    }

    Store createStore = new Store();
    CustomerOrder order = new CustomerOrder();
    Set<Item> set = new HashSet<>();
    Set<Item> menuSet = new HashSet<>();
    Set<CustomerOrder> orderSet = new HashSet<>();
    Set<Reservation> reservationSet = new HashSet<>();

    @BeforeEach
    public void setUp(){
        createStore.setStoreId("S1");
        createStore.setStoreName("THC-HamBurger");

        Address address = new Address();
        address.setAddressId("A2");
        address.setAddressLine1("2740 S Prairie Avenue");
        address.setAddressLine2("Apt 215");
        address.setCity("Chicago");
        address.setState("Illinois");
        address.setZip(60616);
        createStore.setAddress(address);

        Item items = new Item();
        items.setItemId("I1");
        items.setItemDesc("Delicious Nice Burger");
        items.setItemName("HamBurger");
        items.setItemPrice(5.00);
        items.setItemQuantity(1);

        Item items1 = new Item();
        items1.setItemId("I2");
        items1.setItemDesc("Refreshing Fountain Drink");
        items1.setItemName("Fountain Drink");
        items1.setItemPrice(2.00);
        items1.setItemQuantity(1);

        set.add(items);
        set.add(items1);

        menuSet.add(items);
        menuSet.add(items1);


        order.setOrderId("o1");
        order.setSubTotal(7.00);
        order.setTax(2.00);
        order.setTotal(9.00);
        order.setItems(set);
        order.setOrderCreatedDate(new Timestamp(System.currentTimeMillis()));
        orderSet.add(order);
        createStore.setOrders(orderSet);

        Menu menu = new Menu();
        menu.setMenuId("M1");
        menu.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        menu.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        menu.setMenuItems(menuSet);
        createStore.setMenu(menu);

        Reservation reservation = new Reservation();
        reservation.setReservationId("R1");
        reservation.setDate(new Timestamp(System.currentTimeMillis()));
        reservation.setEndTime(new Timestamp(System.currentTimeMillis()));
        reservation.setStartTime(new Timestamp(System.currentTimeMillis()));
        reservationSet.add(reservation);
        createStore.setReservation(reservationSet);

        Mockito.when(storeRepository.findAll())
                .thenReturn(Collections.singletonList(createStore));
        Mockito.when(storeRepository.findStoreByStoreId(createStore.getStoreId()))
                .thenReturn(createStore);
        Mockito.when(storeRepository.findStoreOrders(createStore.getStoreId()))
                .thenReturn(Collections.singletonList(order));
    }

    @AfterEach
    public void cleanUp(){

    }

    @Test
    void createStore() {
        Boolean store = storeService.createStore(createStore);
        assertEquals(store, true, "Store Should be Created");
    }

    @Test
    void updateStore() {
        Boolean store = storeService.updateStore(createStore);
        assertEquals(store, true, "Store Should be Update");
    }

    @Test
    void deleteStore() {
        Boolean store = storeService.deleteStore(createStore.getStoreId());
        assertEquals(store, true, "Store should Be Deleted");
    }

    @Test
    void getStoreById() {
        Store store = storeService.getStoreById(createStore.getStoreId());
        Assertions.assertEquals(createStore, store, "StoreId should Match");
    }

    @Test
    void getAllStores() {
        List<Store> storeList = storeService.getAllStores();
        assertEquals(Collections.singletonList(createStore), storeList, "Store List should match");
    }

    @Test
    void getStoreOrders() {
        List<CustomerOrder> orderList = storeService.getStoreOrders(createStore.getStoreId());
        assertEquals(Collections.singletonList(order), orderList, "Order List should match");
    }
}