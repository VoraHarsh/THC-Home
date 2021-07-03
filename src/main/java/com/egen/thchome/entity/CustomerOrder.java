package com.egen.thchome.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "customer_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "sub_total")
    private double subTotal;

    @Column(name = "total")
    private double total;

    @Column(name = "tax")
    private double tax;

    @OneToMany
    private Set<Item> items;

    @ManyToOne
    @JoinColumn(columnDefinition = "store_id")
    @JsonBackReference
    private Store store;

}
