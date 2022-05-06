package cn.bobdeng.base.user;

import cn.bobdeng.base.TenantId;

public class Users {
    private TenantId tenantId;

    public Users(TenantId tenantId) {

        this.tenantId = tenantId;
    }

    public void newUser(NewUserRequest newUserRequest, UserRepository userRepository) {
        User user = newUserRequest.toUser();
        userRepository.save(new UserDO(this, user));
    }

    public String tenantId() {
        return tenantId.id();
    }
}
