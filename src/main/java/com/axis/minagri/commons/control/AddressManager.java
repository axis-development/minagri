package com.axis.minagri.commons.control;

import com.axis.minagri.commons.entity.Address;
import com.axis.minagri.commons.entity.AddressDTO;
import com.axis.minagri.commons.entity.AddressType;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressManager {

    public Address mapAddressDTOToEntity(AddressDTO dto) {
        if(dto == null) {
            return null;
        }
        return Collections.getFirstOrNull(mapAddressDTOToEntity(Arrays.asList(dto)));
    }

    public  List<Address> mapAddressDTOToEntity(List<AddressDTO> dtos) {
        if(Collections.nullOrEmpty(dtos)) {
            return new ArrayList<>();
        }
        List<Address> entities = dtos.stream().map(dto -> {
            // a simple mapping from domain to dto
            Address address = new Address();
            address.setAddressType(AddressType.fromAddressType(dto.getAddressType()));
            address.setId(dto.getId());
            address.setApartmentNumber(dto.getApartmentNumber());
            address.setCountry(dto.getCountry());
            address.setCity(dto.getCity());
            address.setIsDefault(dto.getIsDefault());
            address.setPostalCode(dto.getPostalCode());
            address.setRemarque(dto.getRemarque());
            address.setStreetName(dto.getStreetName());
            address.setStreetNumber(dto.getStreetNumber());
            return address;
        }).collect(Collectors.toList());
        return entities;
    }

    public AddressDTO mapAddressToDTO(Address address) {
        if(address == null) {
            return null;
        }
        return Collections.getFirstOrNull(mapAddressToDTO(Arrays.asList(address)));
    }

    public static List<AddressDTO> mapAddressToDTO(List<Address> addresses) {
        if(Collections.nullOrEmpty(addresses)) {
            return Lists.newArrayList();
        }
        List<AddressDTO> dtos = addresses.stream().map(address -> {
            // a simple mapping from domain to dto
            AddressDTO dto = new AddressDTO();
            dto.setAddressType(address.getAddressType().name());
            dto.setId(address.getId());
            dto.setApartmentNumber(address.getApartmentNumber());
            dto.setCountry(address.getCountry());
            dto.setCity(address.getCity());
            dto.setIsDefault(address.getIsDefault());
            dto.setPostalCode(address.getPostalCode());
            dto.setRemarque(address.getRemarque());
            dto.setStreetName(address.getStreetName());
            dto.setStreetNumber(address.getStreetNumber());
            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }
}
