package cn.bobdeng.base.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Account {
    private String name;

    public Account(String name) {

        this.name = name;
    }

}
