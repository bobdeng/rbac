package cn.bobdeng.base.user;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserId {
    private Integer id;

    public UserId(int id) {
        this.id = id;
    }

    public Integer id() {
        return id;
    }
}
