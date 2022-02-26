package cn.bobdeng.base.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class UserName {
    private String name;

    public UserName(String name) {
        this.name = name;
    }

    public static UserName empty() {
        return new UserName("");
    }
}
