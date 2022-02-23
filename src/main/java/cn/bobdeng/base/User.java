package cn.bobdeng.base;

public class User {
    private UserId id;

    public static User create() {
        return new User(UserId.create());
    }

    public User(UserId id) {
        this.id = id;
    }

    public UserId getId() {
        return id;
    }
}
