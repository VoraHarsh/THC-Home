package com.egen.thchome.service.impl;

import com.egen.thchome.entity.*;
import com.egen.thchome.enums.MenuStatus;
import com.egen.thchome.enums.ReservationStatus;
import com.egen.thchome.enums.StoreStatus;
import com.egen.thchome.repository.StoreRepository;
import com.egen.thchome.service.StoreService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
    Set<Item> itemSet = new HashSet<>();
    Set<Menu> menuSet = new HashSet<>();
    Set<CustomerOrder> orderSet = new HashSet<>();
    Set<Reservation> reservationSet = new HashSet<>();
    List<OpenHours> openHoursList = new ArrayList<>();

    @BeforeEach
    public void setUp(){

        //Created Store
        createStore.setStoreId("S1");
        createStore.setStoreName("THC-HamBurger");
        createStore.setStoreCreated(new Timestamp(System.currentTimeMillis()));
        createStore.setStoreStatus(StoreStatus.ACTIVE);

        //Created Store Address
        Address address = new Address();
        address.setAddressId("A2");
        address.setAddressLine1("2740 S Prairie Avenue");
        address.setAddressLine2("Apt 215");
        address.setCity("Chicago");
        address.setState("Illinois");
        address.setZip(60616);
        createStore.setAddress(address);

        //Created Store Hours
        OpenHours openHours = new OpenHours();
        openHours.setOpenHoursId("h1");
        openHours.setDay("Monday");
        openHours.setOpenTime(new Timestamp(System.currentTimeMillis()));
        openHours.setCloseTime(new Timestamp(System.currentTimeMillis()));

        OpenHours openHours1 = new OpenHours();
        openHours1.setOpenHoursId("h2");
        openHours1.setDay("Tuesday");
        openHours1.setOpenTime(new Timestamp(System.currentTimeMillis()));
        openHours1.setCloseTime(new Timestamp(System.currentTimeMillis()));

        openHoursList.add(openHours);
        openHoursList.add(openHours1);
        createStore.setHours(openHoursList);

        //Created Items for Menu and Order
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

        itemSet.add(items);
        itemSet.add(items1);

        //Created Store Orders
        order.setOrderId("o1");
        order.setSubTotal(7.00);
        order.setTotal(9.00);
        order.setTax(2.00);
        order.setItems(set);
        order.setOrderCreatedDate(new Timestamp(System.currentTimeMillis()));
        orderSet.add(order);
        createStore.setOrders(orderSet);

        //Created Store Menu
        Menu menu = new Menu();
        menu.setMenuId("M1");
        menu.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        menu.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        menu.setMenuStatus(MenuStatus.ACTIVE);
        menu.setMenuItems(itemSet);
        menuSet.add(menu);
        createStore.setMenu(menuSet);

        //Created Store Reservation
        Reservation reservation = new Reservation();
        reservation.setReservationId("R1");
        reservation.setDate(new Timestamp(System.currentTimeMillis()));
        reservation.setEndTime(new Timestamp(System.currentTimeMillis()));
        reservation.setStartTime(new Timestamp(System.currentTimeMillis()));
        reservation.setReservationCreated(new Timestamp(System.currentTimeMillis()));
        reservation.setReservationStatus(ReservationStatus.RESERVED);
        reservationSet.add(reservation);
        createStore.setReservation(reservationSet);

        Mockito.when(storeRepository.findAll())
                .thenReturn(Collections.singletonList(createStore));
        Mockito.when(storeRepository.findByStoreId(createStore.getStoreId()))
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