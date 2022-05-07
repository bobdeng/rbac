package cn.bobdeng.base.role;

import cn.bobdeng.base.TenantId;

import java.util.Optional;
import java.util.stream.Stream;

public interface RoleRepository {
    void save(RoleDO roleDO);

    Stream<Role> findAll(TenantId tenantId);

    Optional<Role> findById(TenantId tenantId, Integer id);
}
