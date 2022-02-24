package cn.bobdeng.base.user;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserRoleId {
    private int id;

    public UserRoleId(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }
}
