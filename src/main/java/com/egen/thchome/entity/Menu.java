package com.egen.thchome.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {

    @Id
    @Column(name = "menu_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String menuId;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "modified_date")
    private Timestamp modifiedDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JsonManagedReference
    private Set<Item> menuItems;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(columnDefinition = "store_id")
//    @JsonBackReference
//    private Store store;

}
