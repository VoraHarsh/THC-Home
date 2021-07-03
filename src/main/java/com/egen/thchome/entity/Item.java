package com.egen.thchome.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item")
@Data
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_desc")
    private String itemDesc;

    @Column(name = "item_price")
    private double itemPrice;

    @Column(name = "item_quantity")
    private int itemQuantity;

    @ManyToOne
    @JoinColumn(columnDefinition = "menu_id")
    @JsonBackReference
    private Menu menu;
}
