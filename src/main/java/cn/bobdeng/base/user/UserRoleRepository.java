package cn.bobdeng.base.user;

import java.util.Optional;

public interface UserRoleRepository {
    void save(User user, UserRoles userRoles);

    Optional<UserRoleId> findByUser(User user);

    Optional<UserRoles> findUserRoles(User user);

    void saveById(User user, UserRoleId userRoleId, UserRoles userRoles);
}
