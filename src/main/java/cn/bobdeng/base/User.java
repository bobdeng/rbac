package cn.bobdeng.base;

import static cn.bobdeng.base.Users.userMobileRepository;
import static cn.bobdeng.base.Users.userPasswordRepository;

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

    public String id() {
        return id.getId();
    }

    public void bindMobile(Mobile mobile) {
        if (userMobileRepository.findByUser(this).contains(mobile)) {
            return;
        }
        userMobileRepository.save(this, mobile);
    }

    public void setPassword(Password password) {
        userPasswordRepository.save(this,password);
    }
}
