package cn.bobdeng.base.role;

import lombok.Getter;

public class RoleId {
    @Getter
    private Integer id;

    public RoleId(Integer id) {
        this.id = id;
    }

    public static RoleId empty() {
        return new RoleId(null);
    }

    public boolean notEmpty() {
        return this.id != null;
    }
}
