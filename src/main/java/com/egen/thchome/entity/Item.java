package com.egen.thchome.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item")
@Data
public class Item implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_desc")
    private String itemDesc;

    @Column(name = "item_price")
    private double itemPrice;

    @Column(name = "item_quantity")
    private int itemQuantity;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(columnDefinition = "menu_id")
//    @JsonBackReference
//    private Menu menu;
}
