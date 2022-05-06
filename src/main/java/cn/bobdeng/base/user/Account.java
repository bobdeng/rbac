package cn.bobdeng.base.user;

public class Account {
    private String name;
    private UserId userId;

    public Account(String name) {

        this.name = name;
    }

    public String name() {
        return name;
    }

    public Account(AccountDO accountDO) {
        this.name = accountDO.getName();
        this.userId = new UserId(accountDO.getId());
    }

    public boolean isUser(User user) {
        return this.userId.equals(user.getId());
    }
}
