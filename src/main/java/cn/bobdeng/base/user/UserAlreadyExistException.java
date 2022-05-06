package cn.bobdeng.base.user;

import lombok.Getter;

public class UserAlreadyExistException extends Exception {
    @Getter
    private String name;

    public UserAlreadyExistException(String name) {
        this.name = name;
    }
}
