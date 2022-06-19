
package com.axis.caravela.commons.entity;

public enum AddressType {
    Facturation,
    Mailing;

    public static AddressType fromAddressType(String add) {
        if(add == null) {
            return null;
        }
        return valueOf(add);
    }
}
