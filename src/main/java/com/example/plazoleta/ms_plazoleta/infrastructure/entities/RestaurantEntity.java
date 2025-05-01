package com.example.plazoleta.ms_plazoleta.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurants",
        uniqueConstraints = @UniqueConstraint(name = "uq_restaurant_nit", columnNames = "nit"))
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    private String address;
    @Column(name="nit", nullable = false, unique = true, length = 20)
    private String nit;
    private String phoneNumber;
    private String urlLogo;
    private Long ownerId;

    @ElementCollection
    @CollectionTable(
            name = "restaurant_employees",
            joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "employee_id")
    private List<Long> employeesId;
}
