package com.wheel.wheelhouse.entity;

import com.wheel.wheelhouse.tool.CarType;
import com.wheel.wheelhouse.tool.FuelType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    @Column(name = "plate_number")
    private String plateNumber;
    @Column(name = "mark")
    private String mark;
    @Column(name = "module")
    private String module;
    @Column(name = "place_number")
    private Integer placeNumber;
    @Column(name = "assurance_expiration_date")
    private LocalDate assuranceExpDate;
    @Column(name = "price_per_day")
    private Integer price;
    @Column(name = "date_circulation")
    private LocalDate dateCirculation;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel")
    private FuelType fuel;

    @Enumerated(EnumType.STRING)
    @Column(name = "carType")
    private CarType carType;

    //One-to-many with order
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

}
