package cn.bobdeng.base;

import java.util.UUID;

public class UserId {
    private String id;

    public UserId(String id) {
        this.id = id;
    }

    public static UserId create() {
        return new UserId(UUID.randomUUID().toString());
    }

    public String getId() {
        return id;
    }
}
