package cn.bobdeng.base.user;

public class User {
    private UserId id;
    private UserName name;

    public User(UserId id, UserName name) {
        this.id = id;
        this.name = name;
    }

    public String id() {
        return id.id();
    }

    public String name() {
        return name.name();
    }
}
