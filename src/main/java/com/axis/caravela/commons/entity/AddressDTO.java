package com.axis.caravela.commons.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO  {

    private Long id;
    private String streetName;
    private Long streetNumber;
    private String postalCode;
    private String country;
    private String city;
    private String apartmentNumber;
    private String remarque;
    private Boolean isDefault;
    private String addressType;
}
