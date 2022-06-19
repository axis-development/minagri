package com.axis.caravela.company.entity;


import com.axis.caravela.commons.entity.Address;
import com.axis.caravela.customer.entity.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(
        name = "company",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"companyName"})}
)
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "companyName", nullable = false)
    private String companyName;

    @Column(name = "email")
    private String email;

    @Column(name = "gsm")
    private String gsm;

    @Column(name = "phone")
    private String phone;

    @Column(name = "vatNumber")
    private String vatNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();
}
