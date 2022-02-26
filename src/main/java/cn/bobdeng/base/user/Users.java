package cn.bobdeng.base.user;

import java.util.List;

public class Users {
    public static UserRepository userRepository;
    public static UserMobileRepository userMobileRepository;
    public static UserPasswordRepository userPasswordRepository;
    public static PasswordEncoder passwordEncoder;
    public static UserRoleRepository userRoleRepository;
    public static UserAccountRepository accountRepository;
    private TenantId tenantId;

    public Users(TenantId tenantId) {
        this.tenantId = tenantId;
    }

    public Users() {
    }

    public static Users ofTenant(TenantId tenantId) {
        return new Users(tenantId);
    }

    public User newUser() {
        return this.newUser(UserName.empty());
    }

    public User newUser(UserName name) {
        User user = User.create(name);
        userRepository.save(this, user);
        return user;
    }

    public String tenantId() {
        if (this.tenantId == null) {
            return null;
        }
        return this.tenantId.getId();
    }

    public User newAdmin() {
        return this.newAdmin(UserName.empty());
    }

    public User newAdmin(UserName name) {
        User user = User.createAdmin(name);
        userRepository.save(this, user);
        return user;
    }

    public List<User> list() {
        return userRepository.all(this);
    }
}
