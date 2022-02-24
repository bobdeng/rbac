package cn.bobdeng.base.user;

import lombok.EqualsAndHashCode;

import java.util.UUID;
@EqualsAndHashCode
public class UserId {
    private String id;

    public UserId(String id) {
        this.id = id;
    }

    public static UserId create() {
        return new UserId(UUID.randomUUID().toString());
    }

    public static UserId of(String id) {
        return new UserId(id);
    }

    public String getId() {
        return id;
    }
}
