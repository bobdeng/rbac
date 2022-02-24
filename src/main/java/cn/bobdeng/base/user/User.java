package cn.bobdeng.base.user;

import cn.bobdeng.base.role.Function;
import cn.bobdeng.base.role.Roles;

import static cn.bobdeng.base.role.Roles.roleRepository;
import static cn.bobdeng.base.user.Users.*;

public class User {
    private UserId id;
    private UserStatus status;

    public User(UserId id, UserStatus status) {
        this.id = id;
        this.status = status;
    }

    public static User create() {
        User user = new User(UserId.create());
        user.status = UserStatus.active();
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

    public void suspend() {
        UserStatus newStatus = UserStatus.suspend();
        setStatus(newStatus);
    }

    private void setStatus(UserStatus newStatus) {
        this.status = newStatus;
        userRepository.save(this);
    }

    public void remove() {
        setStatus(UserStatus.deleted());
    }

    public void active() {
        setStatus(UserStatus.active());
    }

    public void setRoles(UserRoles userRoles) {
        UserRoleId userRoleId = userRoleRepository.findByUser(this).orElse(null);
        if (userRoleId == null) {
            userRoleRepository.save(this, userRoles);
            return;
        }
        userRoleRepository.saveById(this, userRoleId, userRoles);
    }

    public boolean hasAnyPermission(Function... functions) {
        return userRoleRepository.findUserRoles(this).stream()
                .flatMap(UserRoles::roles)
                .flatMap(roleId -> roleRepository.findById(roleId).stream())
                .anyMatch(role -> role.hasAnyPermission(functions));
    }
}
