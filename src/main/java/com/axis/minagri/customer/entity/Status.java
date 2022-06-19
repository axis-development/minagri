
package com.axis.minagri.customer.entity;

public enum Status {
    Actif,
    Inactif;

    public static Status from(String stat) {
        if(stat == null) {
            return null;
        }
        return valueOf(stat);
    }
}
