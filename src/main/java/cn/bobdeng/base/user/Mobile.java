package cn.bobdeng.base.user;

import lombok.EqualsAndHashCode;

import java.util.Objects;
@EqualsAndHashCode
public class Mobile {
    private String number;

    public Mobile(String number) {
        this.number = number;
    }

    public String number() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Mobile mobile = (Mobile) o;
        return Objects.equals(number, mobile.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
