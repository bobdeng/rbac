package cn.bobdeng.base.user;


public class NewUserRequest {
    private String name;

    public NewUserRequest(String name) {
        this.name = name;
    }

    public User toUser() {
        return new User(new UserName(name));
    }
}
