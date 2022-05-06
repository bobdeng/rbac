package cn.bobdeng.base.user;


import cn.bobdeng.base.TenantId;

import java.util.Optional;

public interface UserRepository {
    UserDO save(UserDO userDO);

    Optional<User> findById(UserId id);

    Optional<User> findByName(TenantId tenantId, String name);
}
