package cn.bobdeng.base.role;

import cn.bobdeng.dummydao.DummyDao;

import java.util.Optional;

public record RoleRepositoryImpl(
        DummyDao<RoleDO, String> roleDao) implements RoleRepository {

    @Override
    public void save(Roles roles, Role role) {
        roleDao.save(RoleDO.builder()
                .id(role.id())
                .functions(role.functionsAsJson())
                .name(role.name())
                .tenantId(roles.tenantId())
                .build());
    }

    @Override
    public Optional<Role> findById(RoleId roleId) {
        return roleDao.findById(roleId.id())
                .map(roleDO -> new Role(roleId, new RoleName(roleDO.getName()), Functions.fromJson(roleDO.getFunctions())));
    }
}
