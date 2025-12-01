package com.wheel.wheelhouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_id;
    @Column(name = "debut_date")
    private LocalDate debutDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "date_location")
    private String debutLocation;
    @Column(name = "fin_location")
    private String finLocation;
    @Column(name = "total_price")
    private Integer totalPrice;
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    // Custom getters for IDs (without @JsonIgnore)
    @JsonProperty("clientId")
    public Long getClientId() {
        return client != null ? client.getClientId() : null;
    }

    @JsonProperty("carId")
    public Long getCarId() {
        return car != null ? car.getCarId() : null;
    }

    @JsonProperty("userId")
    public Long getUserId() {
        return user != null ? user.getUserId() : null;
    }

}
