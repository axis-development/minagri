package com.axis.caravela.customer.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class CustomerDTO implements Serializable {

    private Long id;
    private Long addressId;
    private String firstName;
    private String lastName;
    private String mail;
    private LocalDate clientSince;
    private String clientRating;
    private String phone;
    private String phoneHome;
    private String mobile;
    private String recurrence;
    private String status;
    private String otherContact;
    private String otherContactPosition;
    private LocalDate birthDate;
    private String addressType;
    private String streetName;
    private Long streetNumber;
    private String postalCode;
    private String country;
    private String city;
    private String apartmentNumber;
    private String remarque;
    private String salutation;
    private LocalDate birthday;
    private String critique;
    private String currentUser;
    private String companyName;
    private String vatNumber;
    private String companyMail;
}
