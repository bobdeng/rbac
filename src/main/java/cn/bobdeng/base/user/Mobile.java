package cn.bobdeng.base.user;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Mobile {
    private String number;

    public Mobile(String number) {
        this.number = number;
    }

    public String number() {
        return number;
    }

}
