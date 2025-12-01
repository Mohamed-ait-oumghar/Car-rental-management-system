package com.wheel.wheelhouse.entity;

import com.wheel.wheelhouse.tool.GenderType;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    @Column(name = "client_first_name")
    private String clientFirstName;
    @Column(name = "client_last_name")
    private String clientLastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "cin")
    private String cin;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderType gender;

    @Column(name = "client_city")
    private String clientCity;
    @Column(name = "client_country")
    private String clientCountry;


    //One-to-many with order
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

}
