package cn.bobdeng.base.user;

import cn.bobdeng.base.TenantId;

public class Users {
    private TenantId tenantId;

    public Users(TenantId tenantId) {

        this.tenantId = tenantId;
    }

    public User newUser(NewUserRequest newUserRequest, UserRepository userRepository) throws UserAlreadyExistException {
        User user = newUserRequest.toUser();
        if (userRepository.findByName(tenantId, user.name())
                .isPresent()) {
            throw new UserAlreadyExistException(user.name());
        }
        UserDO userDO = userRepository.save(new UserDO(this, user));
        return new User(userDO);
    }

    public String tenantId() {
        return tenantId.id();
    }
}
