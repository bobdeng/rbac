package cn.bobdeng.base.role;

import java.util.UUID;

public class RoleId {
    private String id;

    public RoleId(String id) {
        this.id = id;
    }

    public static RoleId create() {
        return new RoleId(UUID.randomUUID().toString());
    }

    public static RoleId of(String id) {
        return new RoleId(id);
    }

    public String id() {
        return id;
    }
}
