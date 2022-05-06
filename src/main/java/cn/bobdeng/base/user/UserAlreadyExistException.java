package cn.bobdeng.base.user;

public class UserAlreadyExistException extends Exception {
    private String id;

    public UserAlreadyExistException(String id) {

        this.id = id;
    }

}
