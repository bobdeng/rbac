package cn.bobdeng.base.role;

import cn.bobdeng.base.TenantId;

import java.util.stream.Stream;

public interface RoleRepository {
    void save(RoleDO roleDO);

    Stream<Role> findAll(TenantId tenantId);
}
