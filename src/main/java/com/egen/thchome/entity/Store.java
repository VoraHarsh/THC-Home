package com.egen.thchome.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "store")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "store_id")
    private String storeId;

    @Column(name = "store_name")
    private String storeName;

    @OneToOne
    @JsonManagedReference
    private Address address;

    @ManyToMany
    @JsonManagedReference
    private Set<Reservation> reservation;

    @ManyToMany
    @JsonManagedReference
    private Set<CustomerOrder> orders;

    @OneToOne
    @JsonManagedReference
    private Menu menu;

}
