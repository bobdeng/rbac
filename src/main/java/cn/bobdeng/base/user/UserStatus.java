package cn.bobdeng.base.user;

import java.util.Objects;

public class UserStatus {
    UserStatusEnum status;

    public UserStatus(UserStatusEnum status) {
        this.status = status;
    }

    public static UserStatus active() {
        return new UserStatus(UserStatusEnum.active);
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
