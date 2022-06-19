package com.axis.caravela.commons.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(
        name = "address"
)
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "streetName", nullable = false)
    private String streetName;

    @Column(name = "streetNumber")
    private Long streetNumber;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "apartmentNumber")
    private String apartmentNumber;

    @Column(name = "remarque")
    private String remarque;

    @Column(name = "isDefault")
    private Boolean isDefault;

    @Column(name = "addressType", nullable = false)
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
}
