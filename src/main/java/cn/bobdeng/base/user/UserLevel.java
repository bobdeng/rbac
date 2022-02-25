package cn.bobdeng.base.user;

public class UserLevel {
    public static final String LEVEL_ADMIN = "admin";
    public static final String LEVEL_USER = "user";
    private String level;

    public UserLevel(String level) {
        this.level = level;
    }

    public static UserLevel admin() {
        return new UserLevel(LEVEL_ADMIN);
    }

    public static UserLevel normal() {
        return new UserLevel(LEVEL_USER);
    }

    public static UserLevel of(String level) {
        return new UserLevel(level);
    }

    public boolean isAdmin() {
        return this.level.equals(LEVEL_ADMIN);
    }

    public String name() {
        return level;
    }
}
