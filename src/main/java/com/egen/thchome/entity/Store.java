package com.egen.thchome.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "store_id")
    private String storeId;

    @Column(name = "store_name")
    private String storeName;

    @OneToOne(cascade = CascadeType.ALL)
    //@JsonManagedReference
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    //@JsonManagedReference
    private Set<Reservation> reservation;

    @ManyToMany(cascade = CascadeType.ALL)
    //@JsonManagedReference
    private Set<CustomerOrder> orders;

    @OneToOne(cascade = CascadeType.ALL)
    //@JsonManagedReference
    private Menu menu;

}
