package com.axis.minagri.commons.entity;

import java.util.Objects;

public class BiValue<V1, V2> {
    private V1 value1;
    private V2 value2;

    public BiValue(V1 value1, V2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public V1 getValue1() {
        return value1;
    }

    public void setValue1(V1 value1) {
        this.value1 = value1;
    }

    public V2 getValue2() {
        return value2;
    }

    public void setValue2(V2 value2) {
        this.value2 = value2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiValue<?, ?> biValue = (BiValue<?, ?>) o;
        return Objects.equals(value1, biValue.value1) &&
                Objects.equals(value2, biValue.value2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2);
    }
}
