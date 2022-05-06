package cn.bobdeng.base.user;


public class NewUserRequest {
    private String id;
    private String name;

    public NewUserRequest(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User toUser() {
        return new User(new UserId(id), new UserName(name));
    }
}
