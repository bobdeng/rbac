package cn.bobdeng.base.user;

import lombok.EqualsAndHashCode;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStatus that = (UserStatus) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }
}
