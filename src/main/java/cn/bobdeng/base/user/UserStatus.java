package cn.bobdeng.base.user;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserStatus {
    UserStatusEnum status;

    public UserStatus(UserStatusEnum status) {
        this.status = status;
    }

    public static UserStatus active() {
        return new UserStatus(UserStatusEnum.active);
    }

    public static UserStatus of(String status) {
        return new UserStatus(UserStatusEnum.of(status));
    }

    public static UserStatus suspend() {
        return new UserStatus(UserStatusEnum.suspend);
    }

    public static UserStatus deleted() {
        return new UserStatus(UserStatusEnum.deleted);
    }

}
