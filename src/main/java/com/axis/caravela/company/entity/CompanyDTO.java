package com.axis.caravela.company.entity;


import com.axis.caravela.commons.entity.AddressDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CompanyDTO implements Serializable {

    private Long id;
    private String companyName;
    private String email;
    private String gsm;
    private String phone;
    private String vatNumber;
    private AddressDTO address;
}
