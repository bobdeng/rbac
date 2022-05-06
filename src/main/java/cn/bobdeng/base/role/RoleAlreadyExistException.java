package cn.bobdeng.base.role;

import lombok.Getter;

public class RoleAlreadyExistException extends Exception{
    @Getter
    private String name;

    public RoleAlreadyExistException(String name) {

        this.name = name;
    }
}
