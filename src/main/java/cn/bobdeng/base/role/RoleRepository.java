package cn.bobdeng.base.role;

import java.util.Optional;

public interface RoleRepository {
    void save(Roles roles,Role role);

    Optional<Role> findById(RoleId roleId);
}
