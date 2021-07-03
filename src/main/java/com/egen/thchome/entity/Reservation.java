package com.egen.thchome.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "reservations")
@Data
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String reservationId;

    @Column(name = "reservation_date")
    private Timestamp date;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(columnDefinition = "store_id")
//    @JsonBackReference
//    private Store store;
}
