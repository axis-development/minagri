package com.axis.caravela.customer.control;

import com.axis.caravela.commons.control.Collections;
import com.axis.caravela.commons.control.Enums;
import com.axis.caravela.commons.entity.Address;
import com.axis.caravela.commons.entity.AddressType;
import com.axis.caravela.company.control.CompanyManager;
import com.axis.caravela.company.entity.Company;
import com.axis.caravela.customer.entity.Position;
import com.axis.caravela.commons.control.AddressManager;
import com.axis.caravela.customer.entity.Customer;
import com.axis.caravela.customer.entity.CustomerDTO;
import com.axis.caravela.customer.entity.Salutation;
import com.axis.caravela.security.control.UserService;
import com.axis.caravela.security.entity.User;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerManager {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    AddressManager addressManager;
    @Autowired
    CompanyManager companyManager;
    @Autowired
    UserService userService;

    @Transactional
    public Customer saveCustomer(CustomerDTO dto) throws Exception {
        Customer customer = mapCustomerDTOToEntity(dto);
        if(dto.getCompanyName() != null) {
            Company company = companyManager.findCompanyByName(dto.getCompanyName());
            if(company == null) {
                company = new Company();
                company.setCompanyName(dto.getCompanyName());
                company.setVatNumber(dto.getVatNumber());
            }
            company.getCustomers().add(customer);
            customer.setCompany(company);
            entityManager.merge(company);
        } else {
            entityManager.merge(customer);
        }
        entityManager.flush();
        return customer;
    }

    public Customer findCustomerByMail(String mail) {
        Query query = entityManager.createQuery("select cus from Customer cus where cus.mail = :mail ");
        query.setParameter("mail", mail);
        return (Customer) query.getSingleResult();
    }

    public CustomerDTO findCustomerById(Long id) {
        Query query = entityManager.createQuery("select cus from Customer cus where cus.id = :id ");
        query.setParameter("id", id);
        Customer customer = (Customer) query.getSingleResult();

        return mapCustomerToDTO(customer);
    }

    public List<CustomerDTO> findAllCustomers() {
       List<Customer> customers =  entityManager.createQuery("select cus from Customer cus order by cus.lastName").getResultList();

        return  customers.stream().map(cus -> mapCustomerToDTO(cus)).collect(Collectors.toList());
    }

    public CustomerDTO mapCustomerToDTO(Customer customer) {
        if(customer == null) {
            return null;
        }
        return Collections.getFirst(mapCustomerToDTOs(Arrays.asList(customer)));
    }

    public Customer mapCustomerDTOToEntity(CustomerDTO dto) {
        if(dto == null) {
            return null;
        }
        return Collections.getFirst(mapCustomerDTOToEntity(Arrays.asList(dto)));
    }

    public List<CustomerDTO> mapCustomerToDTOs(List<Customer> customers) {
        if(Collections.nullOrEmpty(customers)) {
            return Lists.newArrayList();
        }
        List<CustomerDTO> dtos = customers.stream().map(customer -> {
            CustomerDTO dto = new CustomerDTO();
            dto.setClientRating(customer.getClientRating());
            dto.setMobile(customer.getMobile());
            dto.setId(customer.getId());
            dto.setPhone(customer.getPhone());
            dto.setClientSince(customer.getClientSince());
            dto.setFirstName(customer.getFirstName());
            dto.setLastName(customer.getLastName());
            dto.setMail(customer.getMail());
            dto.setPhoneHome(customer.getPhoneHome());
            dto.setRecurrence(customer.getRecurrence());
            dto.setAddressId(customer.getAddress().getId());
            dto.setAddressType(Enums.getName(customer.getAddress().getAddressType()));
            dto.setSalutation(Enums.getName(customer.getSalutation()));
            dto.setBirthDate(customer.getBirthDate());
            dto.setStatus(Enums.getName(customer.getStatus()));
            dto.setOtherContact(customer.getOtherContact());
            dto.setOtherContactPosition(Enums.getName(customer.getOtherContactPosition()));
            dto.setCritique(customer.getCritique());
            User user = userService.getCurrentUser();
            dto.setCurrentUser(user.getFirstName() + " "+user.getLastName());
            dto.setOtherContactPosition(Enums.getName(customer.getOtherContactPosition()));
            dto.setBirthDate(customer.getBirthDate());
            if(customer.getCompany() != null) {
                dto.setCompanyName(customer.getCompany().getCompanyName());
                dto.setVatNumber(customer.getCompany().getVatNumber());
                dto.setCompanyMail(customer.getCompany().getEmail());
            }


            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }

    public List<Customer> mapCustomerDTOToEntity(List<CustomerDTO> dtos) {
        if(Collections.nullOrEmpty(dtos)) {
            return Lists.newArrayList();
        }
        List<Customer> entities = dtos.stream().map(dto -> {
            Customer customer = new Customer();
            customer.setClientRating(dto.getClientRating());
            customer.setMobile(dto.getMobile());
            customer.setId(dto.getId());
            customer.setPhone(dto.getPhone());
            customer.setClientSince(dto.getClientSince());
            customer.setFirstName(dto.getFirstName());
            customer.setLastName(dto.getLastName());
            customer.setMail(dto.getMail());
            customer.setPhoneHome(dto.getPhoneHome());
            customer.setRecurrence(dto.getRecurrence());
            customer.setSalutation(Salutation.from(dto.getSalutation()));
            customer.setBirthDate(dto.getBirthDate());
            customer.setRecurrence(dto.getRecurrence());
            customer.setCritique(dto.getCritique());
            customer.setOtherContactPosition(Enums.getFromName(dto.getOtherContactPosition(), Position.class));
             Address address;
             if(dto.getAddressId() != null) {
                 address = entityManager.find(Address.class, dto.getAddressId());
             } else {
                 address = new Address();
             }
             address.setAddressType(AddressType.fromAddressType(dto.getAddressType()));
             address.setCity(dto.getCity());
             address.setCountry(dto.getCountry());
             address.setApartmentNumber(dto.getApartmentNumber());
             address.setRemarque(dto.getRemarque());
             address.setIsDefault(Boolean.TRUE);
             address.setPostalCode(dto.getPostalCode());
             address.setStreetName(dto.getStreetName());
             address.setStreetNumber(dto.getStreetNumber());
             customer.setAddress(address);
             return customer;
        }).collect(Collectors.toList());
        return entities;
    }


}
