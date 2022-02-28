package cn.bobdeng.base.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(Users users, User user);

    Optional<User> findById(TenantId tenantId, UserId userId);

    void save(User user);

    List<User> all(Users users);
}
