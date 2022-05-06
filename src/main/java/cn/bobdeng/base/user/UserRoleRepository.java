package cn.bobdeng.base.user;

import cn.bobdeng.base.role.RoleRepository;

import java.util.Optional;

public interface UserRoleRepository {

    void save(UserRoleDO userRoleDO);

    Optional<UserRoleDO> findById(Integer id);

    RoleRepository roleRepository();
}
