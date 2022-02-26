package cn.bobdeng.base.user;

import cn.bobdeng.base.role.Function;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static cn.bobdeng.base.role.Roles.roleRepository;
import static cn.bobdeng.base.user.Users.*;

@EqualsAndHashCode
@Getter
public class User {
    private UserId id;
    private UserStatus status;
    private UserLevel level;
    private UserName name;

    public User(UserId id, UserStatus status, UserLevel level, UserName name) {
        this.id = id;
        this.status = status;
        this.level = level;
        this.name = name;
    }

    public static User create() {
        return create(UserName.empty());
    }

    public static User create(UserName name) {
        return newUser(UserLevel.normal(), name);
    }

    public User(UserId id) {
        this.id = id;
    }

    public static User createAdmin(UserName name) {
        UserLevel level = UserLevel.admin();
        return newUser(level, name);
    }

    private static User newUser(UserLevel level, UserName name) {
        User user = new User(UserId.create());
        user.status = UserStatus.active();
        user.level = level;
        user.name = name;
        return user;
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
        if (userMobileRepository.findByMobile(mobile).isPresent()) {
            throw new MobileIsUsedByOtherException();
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

    public String statusName() {
        return status.statusName();
    }

    public boolean isAdmin() {
        return level.isAdmin();
    }

    public String levelName() {
        return this.level.name();
    }

    public String name() {
        return name.getName();
    }

    public void bindAccount(Account account) {
        if (accountRepository.findUserByAccount(account)
                .filter(userId -> !userId.equals(this.id))
                .isPresent()) {
            throw new DuplicateAccountException();
        }
        accountRepository.save(this, account);
    }
}
