package cn.bobdeng.base.role;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RoleName {
    private String name;

    public RoleName(String name) {

        this.name = name;
    }

    public String name() {
        return name;
    }
}
