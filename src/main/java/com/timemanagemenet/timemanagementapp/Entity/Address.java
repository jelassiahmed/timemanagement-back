package com.timemanagemenet.timemanagementapp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "ADDRESSES")
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    private String streetName;
    private String streetNumber;
    private String state;
    private String governorate;
    private long zipCode;

    @Column(name = "keycloak_user_id")
    private String keycloakUserId;
}