package com.egen.thchome.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "customer_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "sub_total")
    private double subTotal;

    @Column(name = "total")
    private double total;

    @Column(name = "tax")
    private double tax;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Item> items;

    @Column(name = "created_date")
    private Timestamp orderCreatedDate;

}
