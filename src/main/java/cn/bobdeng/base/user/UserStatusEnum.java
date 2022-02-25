package cn.bobdeng.base.user;

import java.util.stream.Stream;

public enum UserStatusEnum {
    active("active"), suspend("suspend"), deleted("deleted");

    private String status;

    UserStatusEnum(String status) {

        this.status = status;
    }

    public static UserStatusEnum of(String status) {
        return Stream.of(values())
                .filter(userStatusEnum -> userStatusEnum.status.equals(status))
                .findFirst()
                .orElse(UserStatusEnum.active);
    }

    public String getStatus() {
        return status;
    }
}
