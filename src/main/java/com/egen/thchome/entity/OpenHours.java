package com.egen.thchome.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "open_hours")
@AllArgsConstructor
@NoArgsConstructor
public class OpenHours implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String openHoursId;

    @Column(name = "day")
    private String day;
    @Column(name = "open_time")
    private Timestamp openTime;
    @Column(name = "close_time")
    private Timestamp closeTime;

}
