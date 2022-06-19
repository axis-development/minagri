
package com.axis.caravela.commons.entity;

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
