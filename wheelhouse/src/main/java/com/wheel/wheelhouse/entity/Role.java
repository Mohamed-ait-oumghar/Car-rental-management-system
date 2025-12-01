package com.wheel.wheelhouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;


    @Column(name = "role_name", unique = true)
    private String roleName;

    //Many_to-Many with User
    @ManyToMany(mappedBy = "roles" , fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

}
