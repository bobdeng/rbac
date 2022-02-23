package cn.bobdeng.base.user;

import static cn.bobdeng.base.user.Users.userMobileRepository;
import static cn.bobdeng.base.user.Users.userPasswordRepository;

public class User {
    private UserId id;
    private UserStatus status;
    public static User create() {
        User user = new User(UserId.create());
        user.status=UserStatus.active();
        return user;
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
        userPasswordRepository.save(this, password);
    }

    public boolean verifyPassword(Password password) {
        return userPasswordRepository.findByUser(this)
                .filter(encodedPassword -> encodedPassword.match(password))
                .isPresent();
    }

    public UserStatus status() {
        return status;
    }
}
