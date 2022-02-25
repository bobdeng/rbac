package cn.bobdeng.base.role;

import cn.bobdeng.dummydao.DummyDao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public record RoleRepositoryImpl(DummyDao<RoleDO, String> roleDao) implements RoleRepository {

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
                .map(this::getRole);
    }

    private Role getRole(RoleDO roleDO) {
        return new Role(RoleId.of(roleDO.getId()), new RoleName(roleDO.getName()), Functions.fromJson(roleDO.getFunctions()));
    }

    @Override
    public List<Role> list(Roles roles) {
        return roleDao.all()
                .stream()
                .filter(roleDO -> Objects.equals(roleDO.getTenantId(), roles.tenantId()))
                .map(this::getRole)
                .collect(Collectors.toList());
    }
}
