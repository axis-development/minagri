
package com.axis.minagri.commons.entity;

public enum PhoneType {
    Maison,
    Cellulaire,
    Bureau;


    public static PhoneType fromAddressType(String add) {
        if(add == null) {
            return null;
        }
        return valueOf(add);
    }
}
