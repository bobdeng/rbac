package cn.bobdeng.base.user;

import lombok.Getter;

public class UserAccountAlreadyExistException extends RuntimeException{
    @Getter
    private String account;

    public UserAccountAlreadyExistException(String account) {

        this.account = account;
    }
}
