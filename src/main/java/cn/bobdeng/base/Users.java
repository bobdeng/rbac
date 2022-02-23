package cn.bobdeng.base;

public class Users {
    public static UserRepository userRepository;
    public static UserMobileRepository userMobileRepository;
    public static UserPasswordRepository userPasswordRepository;
    public static PasswordEncoder passwordEncoder;
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
        User user = User.create();
        userRepository.save(this, user);
        return user;
    }

    public String tenantId() {
        if (this.tenantId == null) {
            return null;
        }
        return this.tenantId.getId();
    }
}
